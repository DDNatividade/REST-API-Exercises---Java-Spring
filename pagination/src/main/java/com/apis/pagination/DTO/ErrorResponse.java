package com.apis.pagination.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String mensaje;
    private LocalDateTime timestamp;
    private List<String> detalles;

    public ErrorResponse(int status, String mensaje, LocalDateTime timestamp) {
        this.status = status;
        this.mensaje = mensaje;
        this.timestamp = timestamp;
    }

    public ErrorResponse(int status, String mensaje, LocalDateTime timestamp, List<String> detalles) {
        this(status, mensaje, timestamp);
        this.detalles = detalles;
    }

}
