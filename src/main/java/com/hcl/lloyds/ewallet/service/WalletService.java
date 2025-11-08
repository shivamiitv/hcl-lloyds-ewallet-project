package com.hcl.lloyds.ewallet.service;

import com.hcl.lloyds.ewallet.dto.AddMoneyRequest;
import com.hcl.lloyds.ewallet.dto.WalletResponse;
import com.hcl.lloyds.ewallet.entity.*;
import com.hcl.lloyds.ewallet.exception.ResourceNotFoundException;
import com.hcl.lloyds.ewallet.repository.TransactionRepository;
import com.hcl.lloyds.ewallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public WalletResponse getWallet(Long walletId) {
        Wallet w = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        return new WalletResponse(w.getId(), w.getUser().getId(), w.getBalance());
    }

    @Transactional
    public WalletResponse addMoney(AddMoneyRequest req) {
        Wallet w = walletRepository.findById(req.getWalletId())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));

        BigDecimal newBal = w.getBalance().add(req.getAmount());
        w.setBalance(newBal);
        w.setUpdatedAt(Instant.now());
        walletRepository.save(w);

        Transaction t = new Transaction();
        t.setWallet(w);
        t.setAmount(req.getAmount());
        t.setType(TransactionType.CREDIT);
        t.setReference(req.getReference());
        transactionRepository.save(t);

        return new WalletResponse(w.getId(), w.getUser().getId(), w.getBalance());
    }
}
