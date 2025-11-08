package com.hcl.lloyds.ewallet.controller;

import com.hcl.lloyds.ewallet.dto.PaymentRequest;
import com.hcl.lloyds.ewallet.dto.TransactionResponse;
import com.hcl.lloyds.ewallet.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody PaymentRequest req) {
        transactionService.transfer(req);
    }

    @GetMapping("/wallet/{walletId}")
    public List<TransactionResponse> list(@PathVariable Long walletId) {
        return transactionService.listForWallet(walletId);
    }
}
