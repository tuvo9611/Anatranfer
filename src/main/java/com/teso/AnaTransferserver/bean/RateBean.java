package com.teso.AnaTransferserver.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateBean {
    private Long id;
    private String currency;
    private double toVN;
    private double fromVN;
    private boolean status;
}
