package com.gps.device.exception;

public class ErrorDTO {
    public String code;
    public String message;

    public ErrorDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
