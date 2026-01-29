package com.gps.tracking.service;

import com.gps.tracking.clients.VehicleClient;
import com.gps.tracking.dto.VehicleDTO;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class VehicleService {

    private final VehicleClient vehicleClient;

    public VehicleService(@RestClient VehicleClient vehicleClient) {
        this.vehicleClient = vehicleClient;
    }

    @CacheResult(cacheName = "vehicle-cache")
    public VehicleDTO getById(Long id) {
        return vehicleClient.getById(id);
    }
}
