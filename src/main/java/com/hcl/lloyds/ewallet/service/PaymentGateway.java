package com.hcl.lloyds.ewallet.service;

import java.math.BigDecimal;

public interface PaymentGateway {

    /**
     * Talks to actual PG, authorizes money from user card / UPI
     * returns a transaction reference id
     */
    String debit(String customerId, BigDecimal amount);

    /**
     * refunds back the money
     */
    String credit(String customerId, BigDecimal amount);
}
