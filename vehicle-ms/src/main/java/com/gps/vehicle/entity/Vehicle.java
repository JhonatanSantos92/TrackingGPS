package com.gps.vehicle.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "vehiculo")
public class Vehicle extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public String placa;

    public String marca;
    public String modelo;
    public Integer annio;

    public java.time.LocalDateTime fecha_creacion;
    public java.time.LocalDateTime fecha_baja;
}
