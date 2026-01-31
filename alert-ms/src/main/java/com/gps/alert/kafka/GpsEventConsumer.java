package com.gps.alert.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gps.alert.entity.Alert;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class GpsEventConsumer {

    @Incoming("gps-events")
    @Transactional
    public void consume(String json) {
        if (json == null) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            GpsEvent event = mapper.readValue(json, GpsEvent.class);
            Alert alert = new Alert();
            alert.deviceId = event.deviceId;
            alert.vehicleId = event.vehicleId;
            alert.type = "SPEEDING";
            alert.message = "Speed exceeded: " + event.velocidad + " km/h";
            alert.createdAt = java.time.LocalDateTime.now();
            alert.persist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
