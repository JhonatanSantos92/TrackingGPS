package com.gps.tracking.repository;

import com.gps.tracking.entity.Monitoring;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MonitoringRepository implements PanacheRepository<Monitoring> {}