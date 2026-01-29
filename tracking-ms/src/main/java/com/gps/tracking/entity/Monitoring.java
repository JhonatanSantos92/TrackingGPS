package com.gps.tracking.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "monitoreo")
public class Monitoring extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name="assignment_id", nullable = false)
    public Long assignmentId;

    public Double latitud;
    public Double longitud;
    public Double velocidad;
    public java.time.LocalDateTime fecha;
}