package com.gps.device.service;

import com.gps.device.dto.CreateDeviceDTO;
import com.gps.device.dto.UpdateDeviceDTO;
import com.gps.device.entity.Device;
import com.gps.device.exception.BusinessException;
import com.gps.device.repository.DeviceRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DeviceService {

    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Device create(CreateDeviceDTO dto) {
        Device device = new Device();
        device.imei = dto.imei;
        device.modelo = dto.modelo;
        device.estado = "ACTIVE";
        repository.persist(device);
        return device;
    }

    public List<Device> list() {
        return repository.listAll();
    }

    public Device get(Long id) {
        Device device = repository.findById(id);
        if (device == null) {
            throw new BusinessException(404,
                    "USER_NOT_FOUND",
                    "User not found with id: " + id
            );
        }
        return device;
    }

    @Transactional
    public Device update(Long id, UpdateDeviceDTO dto) {
        Device device = repository.findById(id);
        if (device == null) {
            throw new BusinessException(404,
                    "USER_NOT_FOUND",
                    "User not found with id: " + id
            );
        }
        device.modelo = dto.modelo != null ? dto.modelo : device.modelo;
        device.estado = dto.estado != null ? dto.estado : device.estado;
        return device;
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }
}
