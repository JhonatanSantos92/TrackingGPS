package com.gps.device.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "dispositivo")
public class Device extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public String imei;

    public String modelo;

    public String estado;

    public java.time.LocalDateTime fecha_creacion;
    public java.time.LocalDateTime fecha_baja;
}