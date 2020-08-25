package com.teso.AnaTransferserver.enums;

public enum TransactionStatusCode implements CodeEnum {
    NEW(0, "New Transaction"),
    CONFIRM(1, "Confirm"),
    PAYMENT(2, "Payment"),
    COMPLETE(3, "Complete"),
    CANCELED(4, "Canceled")
    ;

    private  int code;
    private String msg;

    TransactionStatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
