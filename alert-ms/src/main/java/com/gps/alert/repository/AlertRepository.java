package com.gps.alert.repository;

import com.gps.alert.entity.Alert;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlertRepository implements PanacheRepository<Alert> {}