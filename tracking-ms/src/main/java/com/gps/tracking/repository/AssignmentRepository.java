package com.gps.tracking.repository;

import com.gps.tracking.entity.Assignment;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AssignmentRepository implements PanacheRepositoryBase<Assignment, Long> {

}