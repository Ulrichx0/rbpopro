package ru.mtuci.rbpopro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.rbpopro.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findByMacAddress(String macAddress);
}
