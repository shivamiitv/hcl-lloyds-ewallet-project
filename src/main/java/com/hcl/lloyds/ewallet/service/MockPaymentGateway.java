package com.hcl.lloyds.ewallet.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class MockPaymentGateway implements PaymentGateway {

    @Override
    public String debit(String customerId, BigDecimal amount) {
        // mock logic: only if amount <= 20000 allow
        if(amount.compareTo(BigDecimal.valueOf(20000)) > 0) {
            throw new RuntimeException("Payment gateway: amount limit exceeded");
        }
        return "PG_DEBIT_" + UUID.randomUUID();
    }

    @Override
    public String credit(String customerId, BigDecimal amount) {
        return "PG_REFUND_" + UUID.randomUUID();
    }
}
