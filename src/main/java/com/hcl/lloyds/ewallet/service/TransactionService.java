package com.hcl.lloyds.ewallet.service;

import com.hcl.lloyds.ewallet.dto.PaymentRequest;
import com.hcl.lloyds.ewallet.dto.TransactionResponse;
import com.hcl.lloyds.ewallet.entity.*;
import com.hcl.lloyds.ewallet.exception.InsufficientBalanceException;
import com.hcl.lloyds.ewallet.exception.ResourceNotFoundException;
import com.hcl.lloyds.ewallet.repository.TransactionRepository;
import com.hcl.lloyds.ewallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void transfer(PaymentRequest req) {
        if (req.getFromWalletId().equals(req.getToWalletId()))
            throw new IllegalArgumentException("Source and destination wallet cannot be same");

        Wallet from = walletRepository.findById(req.getFromWalletId())
                .orElseThrow(() -> new ResourceNotFoundException("From-wallet not found"));
        Wallet to = walletRepository.findById(req.getToWalletId())
                .orElseThrow(() -> new ResourceNotFoundException("To-wallet not found"));

        BigDecimal amt = req.getAmount();
        if (from.getBalance().compareTo(amt) < 0)
            throw new InsufficientBalanceException("Insufficient balance");

        from.setBalance(from.getBalance().subtract(amt));
        to.setBalance(to.getBalance().add(amt));
        from.setUpdatedAt(Instant.now());
        to.setUpdatedAt(Instant.now());
        walletRepository.save(from);
        walletRepository.save(to);

        Transaction debit = new Transaction();
        debit.setWallet(from);
        debit.setAmount(amt);
        debit.setType(TransactionType.TRANSFER_OUT);
        debit.setReference(req.getReference());
        transactionRepository.save(debit);

        Transaction credit = new Transaction();
        credit.setWallet(to);
        credit.setAmount(amt);
        credit.setType(TransactionType.TRANSFER_IN);
        credit.setReference(req.getReference());
        transactionRepository.save(credit);
    }

    public List<TransactionResponse> listForWallet(Long walletId) {
        Wallet w = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        return transactionRepository.findByWalletOrderByCreatedAtDesc(w).stream().map(t -> {
            TransactionResponse r = new TransactionResponse();
            r.setId(t.getId());
            r.setType(t.getType());
            r.setAmount(t.getAmount());
            r.setReference(t.getReference());
            r.setCreatedAt(t.getCreatedAt());
            return r;
        }).collect(Collectors.toList());
    }
}
