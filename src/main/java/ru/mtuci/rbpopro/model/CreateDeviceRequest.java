package ru.mtuci.rbpopro.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateDeviceRequest {
     private String name;
     private String macAddress;
     private Long userId;
}
