package com.teso.AnaTransferserver.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document (collection="users")
public class User implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long userId;

    private String userImage;
    private String userEmail;
    private String userPassword;
    private String userCountry;
    private Bank bank;
    @DBRef
    private Set<Role> roles;
    private String accountNumber;
    private int phone;
    private String address;

    @DBRef
    private List<Recipient> myRecipients;
    private LocalDate birth;
    private int pinCode;String userFullName;
    private boolean enable;
}
