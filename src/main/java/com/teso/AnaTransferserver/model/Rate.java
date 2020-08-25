package com.teso.AnaTransferserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="rates")
public class Rate {
    @Id
    private Long id;
    private String currency;
    private double toVN;
    private double fromVN;
    private boolean status;
}
