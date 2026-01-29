package com.gps.alert.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "alerta")
public class Alert extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name="device_id")
    public Long deviceId;

    @Column(name="vehicle_id")
    public Long vehicleId;

    public String type;
    public String message;

    @Column(name="created_at")
    public java.time.LocalDateTime createdAt;
}