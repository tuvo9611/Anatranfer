package com.teso.AnaTransferserver.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipientBean {

    private Long recipientId;

    private String firstNameCustomer;
    private String lastNameCustomer;
    private LocalDate dateCustomer;
    private Boolean genderCustomer;
    private String emailCustomer;
    private Integer mobileCustomer;
    private String bankNameCustomer;
    private String accountNameCustomer;
    private String swiftCodeCustomer;
    private String reasonToSendCustomer;
}
