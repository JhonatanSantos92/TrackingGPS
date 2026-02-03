package com.gps.device.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "dispositivo")
public class Device extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "imei", nullable = false)
    public String imei;
    @Column(name = "modelo", nullable = false)
    public String modelo;
    @Column(name = "estado", nullable = false)
    public String estado;
}