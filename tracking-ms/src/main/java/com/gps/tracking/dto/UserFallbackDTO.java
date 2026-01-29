package com.gps.tracking.dto;

public class UserFallbackDTO {

    public static UserDTO empty(Long id) {
        UserDTO dto = new UserDTO();
        dto.id = id;
        dto.nombre = "UNKNOWN";
        dto.correo = null;
        dto.status = "INACTIVE";
        return dto;
    }
}
