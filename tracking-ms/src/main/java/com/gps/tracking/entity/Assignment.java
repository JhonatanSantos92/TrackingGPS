package com.gps.tracking.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "asignacion")
public class Assignment extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name="device_id", nullable = false)
    public Long deviceId;

    @Column(name="vehicle_id", nullable = false)
    public Long vehicleId;

    @Column(name="user_id")
    public Long userId;

    public java.time.LocalDateTime fecha_creacion;
}