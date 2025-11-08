package com.hcl.lloyds.ewallet.dto;

import java.math.BigDecimal;

public class AddMoneyRequest {
    private Long walletId;
    private BigDecimal amount;
    private String reference;

    public Long getWalletId() { return walletId; }
    public void setWalletId(Long walletId) { this.walletId = walletId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
}
