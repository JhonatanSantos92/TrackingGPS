package com.gps.tracking.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "monitoreo")
public class Monitoring extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name="assignment_id", nullable = false)
    public Long assignmentId;

    @Column(nullable = false, precision = 9, scale = 6)
    public BigDecimal latitud;

    @Column(nullable = false, precision = 9, scale = 6)
    public BigDecimal longitud;

    @Column(precision = 5, scale = 2)
    public BigDecimal velocidad;

    @Column(nullable = false)
    public LocalDateTime fecha;
}