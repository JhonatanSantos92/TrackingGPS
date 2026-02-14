package com.gps.tracking.clients;

import com.gps.tracking.dto.VehicleDTO;
import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class VehicleService {

    @RestClient
    @Inject
    private VehicleClient vehicleClient;

    @Timeout(3000)
    @Retry(maxRetries = 2, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 10
    )
    @Fallback(fallbackMethod = "fallbackVehicle")
    @CacheResult(cacheName = "vehicle-cache")
    public Uni<VehicleDTO> getById(Long id) {
        return vehicleClient.getById(id);
    }

    public Uni<VehicleDTO> fallbackVehicle(Long id) {
        return Uni.createFrom().item(
                new VehicleDTO() {{
                    this.id = id;
                    this.placa = "N/A";
                    this.marca = "N/A";
                    this.modelo = "Vehículo no disponible";
                    this.annio = 9999;
                }}
        );
    }
}
