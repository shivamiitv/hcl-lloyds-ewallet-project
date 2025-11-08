package com.hcl.lloyds.ewallet.service;

import com.hcl.lloyds.ewallet.dto.CreateUserRequest;
import com.hcl.lloyds.ewallet.entity.User;
import com.hcl.lloyds.ewallet.entity.Wallet;
import com.hcl.lloyds.ewallet.repository.UserRepository;
import com.hcl.lloyds.ewallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public UserService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public User createUser(CreateUserRequest req) {
        User u = new User();
        u.setName(req.getName());
        u.setPhone(req.getPhone());
        u = userRepository.save(u);

        Wallet w = new Wallet();
        w.setUser(u);
        walletRepository.save(w);
        return u;
    }
}
