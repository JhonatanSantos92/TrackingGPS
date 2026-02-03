package com.gps.device.service;

import com.gps.device.dto.CreateDeviceDTO;
import com.gps.device.dto.UpdateDeviceDTO;
import com.gps.device.entity.Device;
import com.gps.device.exception.BusinessException;
import com.gps.device.repository.DeviceRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DeviceService {

    @Inject
    private DeviceRepository repository;

    @WithTransaction
    public Uni<Device> create(CreateDeviceDTO dto) {
        Device device = new Device();
        device.imei = dto.imei;
        device.modelo = dto.modelo;
        device.estado = "ACTIVE";
        return repository.persist(device);
    }

    public Uni<List<Device>> list() {
        return repository.listAll();
    }

    public Uni<Device> get(Long id) {
        return repository.findById(id)
                .onItem().ifNull().failWith(() ->
                        new BusinessException(
                                404,
                                "USER_NOT_FOUND",
                                "User not found with id: " + id
                        )
                );
    }

    @WithTransaction
    public Uni<Device> update(Long id, UpdateDeviceDTO dto) {

        return repository.findById(id)
                .onItem().ifNull().failWith(() ->
                        new BusinessException(
                                404,
                                "USER_NOT_FOUND",
                                "User not found with id: " + id
                        )
                )
                .map(device -> {
                    if (dto.modelo != null)
                        device.modelo = dto.modelo;
                    if (dto.estado != null)
                        device.estado = dto.estado;
                    return device;
                });
    }
}
