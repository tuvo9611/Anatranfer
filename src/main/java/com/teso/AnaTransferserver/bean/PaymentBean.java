package com.teso.AnaTransferserver.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentBean {
    private Long paymentId;

    private String paymentImage;
    private String paymentName;
    private String paymentDescription;
    private String paymentCredentialTitle;
    private double paymentCredentialValue;
    private Integer paymentStatus;
}
