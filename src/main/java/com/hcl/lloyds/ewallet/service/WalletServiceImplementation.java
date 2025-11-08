package com.hcl.lloyds.ewallet.service;

import com.hcl.lloyds.ewallet.entity.User;
import com.hcl.lloyds.ewallet.entity.Wallet;
import com.hcl.lloyds.ewallet.repository.UserRepository;
import com.hcl.lloyds.ewallet.repository.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



//import java.util.Currency;


@Service
public class WalletServiceImplementation implements WalletService{
	
	@Autowired
	WalletRepository walletRepo;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public Wallet createWallet(long userId, Wallet wallet) {
		
		 User user = userRepo.findById(userId)
		            .orElseThrow(() -> new RuntimeException("User not found"));		
		wallet.setUser(user);
		return walletRepo.save(wallet);
	}

	@Override
	public Wallet getUserWallet(long userId) {
		
		return new Wallet();
//		return walletRepo.findById(userId).orElseThrow("User not found");
	}
}
