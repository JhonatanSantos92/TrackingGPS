package com.gps.tracking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TrackingResponseDTO {

    public BigDecimal latitud;
    public BigDecimal longitud;
    public BigDecimal velocidad;
    public LocalDateTime fecha;

    public DeviceDTO device;
    public VehicleDTO vehicle;
    public UserDTO user;

}