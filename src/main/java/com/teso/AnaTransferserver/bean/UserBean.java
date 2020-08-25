package com.teso.AnaTransferserver.bean;

import com.teso.AnaTransferserver.model.Recipient;
import com.teso.AnaTransferserver.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {
    private Long userId;
    private String userImage;
    private String userEmail;
    private String userPassword;
    private String userCountry;

    private String accountNumber;
    private int phone;
    private String address;

    private LocalDate birth;
    private int pinCode;String userFullName;
    private boolean enable;

    private List<Recipient> myRecipients;
    private Set<Role> roles;
}
