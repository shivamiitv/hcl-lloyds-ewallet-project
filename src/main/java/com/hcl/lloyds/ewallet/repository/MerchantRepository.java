package com.hcl.lloyds.ewallet.repository;

import com.hcl.lloyds.ewallet.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
}
