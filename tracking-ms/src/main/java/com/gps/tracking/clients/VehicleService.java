package com.gps.tracking.clients;

import com.gps.tracking.dto.VehicleDTO;
import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class VehicleService {

    @RestClient
    @Inject
    private VehicleClient vehicleClient;

    @CacheResult(cacheName = "vehicle-cache")
    public Uni<VehicleDTO> getById(Long id) {
        return vehicleClient.getById(id);
    }
}
