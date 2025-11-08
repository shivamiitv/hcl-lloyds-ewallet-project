package com.hcl.lloyds.ewallet.controller;

import com.hcl.lloyds.ewallet.dto.AddMoneyRequest;
import com.hcl.lloyds.ewallet.dto.WalletResponse;
import com.hcl.lloyds.ewallet.entity.User;
import com.hcl.lloyds.ewallet.entity.Wallet;
import com.hcl.lloyds.ewallet.service.WalletService;
import com.hcl.lloyds.ewallet.service.WalletServiceImplementation;

import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/wallet")
public class WalletController {
	
	@Autowired
	WalletService walletService;

    @PostMapping("/v1/{user-id}/create-wallet")
    public Wallet createWallet(@PathVariable long userId, Wallet wallet) {
    	return walletService.createWallet(userId, wallet);
    }
    
    @GetMapping("/v1/{user-id}/")
    public Wallet getUserWallet(@PathVariable long userId) {
    	return walletService.getUserWallet(userId);
    }    
}
