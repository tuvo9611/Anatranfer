package com.teso.AnaTransferserver.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankBean {
    private Long bankId;
    private String bankBIC;
    private String bankName;
    private String bankImage;
}
