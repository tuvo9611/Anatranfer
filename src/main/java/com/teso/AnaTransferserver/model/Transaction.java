package com.teso.AnaTransferserver.model;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="transactions")
public class Transaction {
    @Id
    private Long transactionId;

    private double transactionYourSend;
    private double transactionTheyRecevie;
    private double transactionFee;
    private Integer transactionMethod;
    private Integer transactionStatus;
    private double transactionTotal;
    private double transactionDiscount;
    @NonNull
    private User userSend;
    private User userGet;
    private Payment payment;
    private Bank bank;
    private List<String> bill;
    @NonNull
    private LocalDate createDate;
}
