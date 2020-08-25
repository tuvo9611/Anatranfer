package com.teso.AnaTransferserver.model;

import lombok.Data;

@Data
public class ChangePassword {
    private String password;
    private String newPassword;
}
