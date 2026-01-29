package com.gps.tracking.repository;

import com.gps.tracking.entity.Assignment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AssignmentRepository implements PanacheRepository<Assignment> {}