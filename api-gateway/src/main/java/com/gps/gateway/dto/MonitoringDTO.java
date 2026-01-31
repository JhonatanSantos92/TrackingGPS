package com.gps.gateway.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MonitoringDTO {
    public Long id;
    public Long assignmentId;
    public BigDecimal latitud;
    public BigDecimal longitud;
    public BigDecimal velocidad;
    public LocalDateTime fecha;
}
