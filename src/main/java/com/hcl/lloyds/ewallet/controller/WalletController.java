package com.hcl.lloyds.ewallet.controller;

import com.hcl.lloyds.ewallet.dto.AddMoneyRequest;
import com.hcl.lloyds.ewallet.dto.WalletResponse;
import com.hcl.lloyds.ewallet.service.WalletService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{id}")
    public WalletResponse get(@PathVariable Long id) {
        return walletService.getWallet(id);
    }

    @PostMapping("/add-money")
    public WalletResponse add(@RequestBody AddMoneyRequest req) {
        return walletService.addMoney(req);
    }
}
