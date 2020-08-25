package com.teso.AnaTransferserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("/recipients")
public class Recipient {
    @Id
    private Long recipientId;

    private String firstName;
    private String lastName;
    private LocalDate date;
    private Boolean gender;
    private String email;
    private Integer mobile;
    private String bankName;
    private String accountName;
    private String swiftCode;
    private String reasonToSend;

}
