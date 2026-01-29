package com.gps.tracking.service;

import com.gps.tracking.clients.DeviceClient;
import com.gps.tracking.dto.DeviceDTO;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class DeviceService {

    private final DeviceClient deviceClient;

    public DeviceService(@RestClient DeviceClient deviceClient) {
        this.deviceClient = deviceClient;
    }

    @CacheResult(cacheName = "device-cache")
    public DeviceDTO getById(Long id) {
        return deviceClient.getById(id);
    }
}
