package com.hcl.lloyds.ewallet.dto;

import java.math.BigDecimal;
import java.time.Instant;
import com.hcl.lloyds.ewallet.entity.TransactionType;

public class TransactionResponse {
    private Long id;
    private TransactionType type;
    private BigDecimal amount;
    private String reference;
    private Instant createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
