package com.hcl.lloyds.ewallet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;


@Entity
@Table(name = "wallets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id", nullable =false, unique=true)
    private Long walletId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false) 
    private User user;

    @Column(name ="balance", nullable = false, precision = 12, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name ="updated_at", nullable = false)
    private Instant updatedAt = Instant.now();
    
//    @Column(name = "bank_id", nullable = false)
//    private int bankId;

    
}
