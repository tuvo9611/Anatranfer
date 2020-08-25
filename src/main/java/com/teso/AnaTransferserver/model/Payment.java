package com.teso.AnaTransferserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="payments")
public class Payment {
    @Id
    private Long paymentId;

    private String paymentImage;
    private String paymentName;
    private String paymentDescription;
    private String paymentCredentialTitle;
    private double paymentCredentialValue;
    private Integer paymentStatus;
}
