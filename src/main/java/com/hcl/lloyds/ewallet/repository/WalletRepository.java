package com.hcl.lloyds.ewallet.repository;

import com.hcl.lloyds.ewallet.entity.Wallet;
import com.hcl.lloyds.ewallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}
