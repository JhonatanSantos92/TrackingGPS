package com.gps.tracking.clients;

import com.gps.tracking.dto.DeviceDTO;
import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class DeviceService {

    @Inject
    @RestClient
    private DeviceClient deviceClient;

    @CacheResult(cacheName = "device-cache")
    public Uni<DeviceDTO> getById(Long id) {
        return deviceClient.getById(id);
    }
}
