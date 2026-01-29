package com.gps.alert.service;

import com.gps.alert.dto.CreateAlertDTO;
import com.gps.alert.entity.Alert;
import com.gps.alert.repository.AlertRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AlertService {

    private final AlertRepository repository;

    public AlertService(AlertRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Alert processEvent(CreateAlertDTO dto) {
        Alert alert = new Alert();

        if (dto.speed != null && dto.speed > 80) {
            alert.type = "OVER_SPEED";
            alert.message = "Velocidad excedida: " + dto.speed + " km/h";
        } else {
            return null; // no hay alerta
        }

        alert.deviceId = dto.deviceId;
        alert.vehicleId = dto.vehicleId;
        alert.createdAt = java.time.LocalDateTime.now();

        repository.persist(alert);
        return alert;
    }
}