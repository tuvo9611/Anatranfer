package com.teso.AnaTransferserver.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoteCodeBean {
    private Long promoteCodeId;

    private String promoteCodeName;
    private double promoteCodeDiscount;
    private Date expectDate;
}
