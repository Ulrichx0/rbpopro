package ru.mtuci.rbpopro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtuci.rbpopro.model.*;
import ru.mtuci.rbpopro.repository.DeviceRepository;
import ru.mtuci.rbpopro.repository.DeviceLicenceRepository;
import ru.mtuci.rbpopro.repository.LicenceRepository;
import ru.mtuci.rbpopro.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceLicenceRepository deviceLicenceRepository;
    private final LicenceRepository licenceRepository;
    private final UserRepository userRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository,
                         DeviceLicenceRepository deviceLicenceRepository,
                         LicenceRepository licenceRepository,
                         UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceLicenceRepository = deviceLicenceRepository;
        this.licenceRepository = licenceRepository;
        this.userRepository = userRepository;
    }

    // Create a new device
    public Device createDevice(CreateDeviceRequest request) {
        Device device = deviceRepository.findByMacAddress(request.getMacAddress());
        if (device != null) {
            return device;
        } else {
            Device newDevice = new Device();
            newDevice.setName(request.getName());
            newDevice.setMacAddress(request.getMacAddress());
            User user = userRepository.findById(request.getUserId()).get();
            newDevice.setUser(user);
            return deviceRepository.save(device);
        }
    }

    // Create a device-licence relationship
    public DeviceLicence createDeviceLicence(Long deviceId, Long licenceId) {
        Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        Optional<Licence> licenceOptional = licenceRepository.findById(licenceId);

        if (deviceOptional.isPresent() && licenceOptional.isPresent()) {
            DeviceLicence deviceLicence = new DeviceLicence();
            deviceLicence.setDevice(deviceOptional.get());
            deviceLicence.setLicence(licenceOptional.get());
            deviceLicence.setActivationDate(new Date());
            return deviceLicenceRepository.save(deviceLicence);
        } else {
            throw new IllegalArgumentException("Device or Licence not found");
        }
    }

    // Get a device by MAC address
    public Device getDeviceByMacAddress(String macAddress) {
        return deviceRepository.findByMacAddress(macAddress);
    }

    // Get Device by ID
    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }
}
