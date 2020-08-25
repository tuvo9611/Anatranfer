package com.teso.AnaTransferserver.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="stores")
public class Store {
    @Id
    private Long storeId;

    private String storeImage;
    private String storeAddress;
    private String storeCity;
    private Integer storeStatus;

}
