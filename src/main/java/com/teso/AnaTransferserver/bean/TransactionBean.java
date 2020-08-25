package com.teso.AnaTransferserver.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionBean {
    private Long transactionId;
    private double transactionYourSend;
    private double transactionTheyRecevie;
    private double transactionFee;
    private Integer transactionMethod;
    private Integer transactionStatus;
    private double transactionTotal;
    private double transactionDiscount;
    private LocalDate createDate;
}
