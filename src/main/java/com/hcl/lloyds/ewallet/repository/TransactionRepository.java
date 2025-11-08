package com.hcl.lloyds.ewallet.repository;

import com.hcl.lloyds.ewallet.entity.Transaction;
import com.hcl.lloyds.ewallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWalletOrderByCreatedAtDesc(Wallet wallet);
}
