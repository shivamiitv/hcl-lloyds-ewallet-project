package com.hcl.lloyds.ewallet.repository;

import com.hcl.lloyds.ewallet.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
