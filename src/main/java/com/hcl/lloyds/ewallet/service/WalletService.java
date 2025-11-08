package com.hcl.lloyds.ewallet.service;

import org.springframework.stereotype.Service;

import com.hcl.lloyds.ewallet.entity.Wallet;


public interface WalletService {
	Wallet createWallet(long userId, Wallet wallet);

	Wallet getUserWallet(long userId);
}
