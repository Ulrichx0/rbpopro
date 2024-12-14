package ru.mtuci.rbpopro.model;

import lombok.Data;

import java.util.Date;

@Data
public class Ticket {
    private Date serverDate;          // Текущая дата сервера
    private Long ticketLifetime;      // Время жизни тикета (например, в секундах)
    private Date activationDate;      // Дата активации лицензии
    private Date expirationDate;      // Дата истечения лицензии
    private Long userId;              // Идентификатор пользователя
    private String deviceId;          // Идентификатор устройства
    private Boolean licenceBlocked;   // Флаг блокировки лицензии
    private String digitalSignature;  // Цифровая подпись
}
