package com.gps.tracking.clients;

import com.gps.tracking.dto.DeviceDTO;
import com.gps.tracking.dto.UserDTO;
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
public class DeviceService {

    @Inject
    @RestClient
    private DeviceClient deviceClient;

    @Timeout(3000)
    @Retry(maxRetries = 2, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 10
    )
    @Fallback(fallbackMethod = "fallbackDevice")
    @CacheResult(cacheName = "device-cache")
    public Uni<DeviceDTO> getById(Long id) {
        return deviceClient.getById(id);
    }

    public Uni<DeviceDTO> fallbackDevice(Long id) {
        return Uni.createFrom().item(
                new DeviceDTO() {{
                    this.id = id;
                    this.imei = "N/A";
                    this.modelo = "Dispositivo no disponible";
                    this.estado = "Desconocido";
                }}
        );
    }
}
