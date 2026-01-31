package com.gps.tracking.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class GpsEventProducer {

    @Inject
    @Channel("gps-events")
    Emitter<GpsEvent> emitter;

    public void send(GpsEvent event) {
        emitter.send(event);
    }
}