package com.gps.vehicle.service;

import com.gps.vehicle.dto.CreateVehicleDTO;
import com.gps.vehicle.dto.UpdateVehicleDTO;
import com.gps.vehicle.entity.Vehicle;
import com.gps.vehicle.exception.BusinessException;
import com.gps.vehicle.repository.VehicleRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class VehicleService {

    @Inject
    private VehicleRepository repository;

    @WithTransaction
    public Uni<Vehicle> create(CreateVehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.placa = dto.placa;
        vehicle.marca = dto.marca;
        vehicle.modelo = dto.modelo;
        vehicle.annio = dto.annio;
        return repository.persist(vehicle);
    }

    @WithSession
    public Uni<List<Vehicle>> findAll() {
        return repository.listAll();
    }


    public Multi<Vehicle> list() {
        return this.findAll()
                .onItem()
                .transformToMulti(list -> Multi.createFrom().iterable(list));
    }

    public Uni<Vehicle> get(Long id) {
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
    public Uni<Vehicle> update(Long id, UpdateVehicleDTO dto) {
        return repository.findById(id)
                .onItem().ifNull().failWith(() ->
                        new BusinessException(
                                404,
                                "USER_NOT_FOUND",
                                "User not found with id: " + id
                        )
                )
                .flatMap(vehicle -> {
                    vehicle.marca = dto.marca;
                    vehicle.modelo = dto.modelo;
                    vehicle.annio = dto.annio;
                    return repository.persist(vehicle);
                });
    }

    @WithTransaction
    public Uni<Void> delete(Long id) {
        return repository.findById(id)
                .onItem().ifNull().failWith(() ->
                        new BusinessException(
                                404,
                                "USER_NOT_FOUND",
                                "User not found with id: " + id
                        )
                )
                .flatMap(vehicle -> repository.delete(vehicle));
    }
}
