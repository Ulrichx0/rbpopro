package ru.mtuci.rbpopro.model;

import lombok.Data;

import java.util.Date;

@Data
public class Ticket {
    private Date serverDate;
    private Long ticketLifetime;
    private Date activationDate;
    private Date expirationDate;
    private Long userId;
    private String deviceId;
    private Boolean licenceBlocked;
    private String digitalSignature;
}
