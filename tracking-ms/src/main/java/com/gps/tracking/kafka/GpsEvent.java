package com.gps.tracking.kafka;

import java.math.BigDecimal;

public class GpsEvent {

    public Long deviceId;
    public Long vehicleId;

    public BigDecimal latitud;
    public BigDecimal longitud;
    public BigDecimal velocidad;

}
