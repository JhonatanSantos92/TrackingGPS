package com.gps.vehicle.service;

import com.gps.vehicle.dto.CreateVehicleDTO;
import com.gps.vehicle.dto.UpdateVehicleDTO;
import com.gps.vehicle.entity.Vehicle;
import com.gps.vehicle.repository.VehicleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class VehicleService {

    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Vehicle create(CreateVehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.placa = dto.placa;
        vehicle.marca = dto.marca;
        vehicle.modelo = dto.modelo;
        vehicle.annio = dto.annio;
        repository.persist(vehicle);
        return vehicle;
    }

    public List<Vehicle> list() {
        return repository.listAll();
    }

    public Vehicle get(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Vehicle update(Long id, UpdateVehicleDTO dto) {
        Vehicle vehicle = repository.findById(id);
        if (vehicle == null) return null;
        vehicle.marca = dto.marca != null ? dto.marca : vehicle.marca;
        vehicle.modelo = dto.modelo != null ? dto.modelo : vehicle.modelo;
        vehicle.annio = dto.annio != null ? dto.annio : vehicle.annio;
        return vehicle;
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }
}
