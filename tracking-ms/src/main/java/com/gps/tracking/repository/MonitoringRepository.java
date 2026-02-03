package com.gps.tracking.repository;

import com.gps.tracking.entity.Monitoring;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MonitoringRepository implements PanacheRepositoryBase<Monitoring, Long> {

    public Uni<List<Monitoring>> listByAssignment(Long assignmentId) {
        return find("assignmentId", assignmentId).list();
    }
}