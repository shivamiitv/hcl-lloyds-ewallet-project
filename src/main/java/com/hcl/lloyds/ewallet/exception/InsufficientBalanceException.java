package com.hcl.lloyds.ewallet.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String msg) { super(msg); }
}
