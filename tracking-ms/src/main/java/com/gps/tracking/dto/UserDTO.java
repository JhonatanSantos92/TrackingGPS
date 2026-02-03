package com.gps.tracking.dto;

public class UserDTO {

    public Long id;
    public String nombre;
    public String correo;
    public String status;

    public UserDTO() {
    }

    public UserDTO(Long id) {
        this.id = id;
    }
}