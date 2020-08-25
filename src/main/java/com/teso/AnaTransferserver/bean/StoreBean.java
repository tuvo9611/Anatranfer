package com.teso.AnaTransferserver.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreBean {
    private Long storeId;
    private String storeImage;
    private String storeAddress;
    private String storeCity;
    private Integer storeStatus;
}
