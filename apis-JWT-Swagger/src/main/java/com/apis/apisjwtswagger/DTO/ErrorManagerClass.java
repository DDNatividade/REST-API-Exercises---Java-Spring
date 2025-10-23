package com.apis.apisjwtswagger.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorManagerClass {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    private List<String> errors;

    public ErrorManagerClass(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ErrorManagerClass(int status, String message, LocalDateTime timestamp,
                             List<String> errors) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.errors = errors;

    }

}
