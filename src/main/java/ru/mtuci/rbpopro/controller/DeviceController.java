package ru.mtuci.rbpopro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.rbpopro.model.CreateDeviceRequest;
import ru.mtuci.rbpopro.model.Device;
import ru.mtuci.rbpopro.model.DeviceLicence;
import ru.mtuci.rbpopro.service.DeviceService;

import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // Endpoint to create a new device
    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody CreateDeviceRequest device) {
        Device createdDevice = deviceService.createDevice(device);
        return ResponseEntity.ok(createdDevice);
    }

    // Endpoint to get a device by MAC address
    @GetMapping("/mac/{macAddress}")
    public ResponseEntity<Device> getDeviceByMacAddress(@PathVariable String macAddress) {
        Device device = deviceService.getDeviceByMacAddress(macAddress);
        if (device != null) {
            return ResponseEntity.ok(device);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to assign a licence to a device
    @PostMapping("/{deviceId}/licence/{licenceId}")
    public ResponseEntity<DeviceLicence> assignLicenceToDevice(@PathVariable Long deviceId, @PathVariable Long licenceId) {
        try {
            DeviceLicence deviceLicence = deviceService.createDeviceLicence(deviceId, licenceId);
            return ResponseEntity.ok(deviceLicence);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Endpoint to get a device by ID
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Optional<Device> device = deviceService.getDeviceById(id);
        return device.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
