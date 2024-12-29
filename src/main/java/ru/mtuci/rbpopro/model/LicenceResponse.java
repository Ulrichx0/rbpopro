package ru.mtuci.rbpopro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenceResponse {
    private String code;
    private Long ownerId;
    private String licenceType;
    private String productName;
    private Integer deviceCount;
    private Integer duration;
}
