package com.apis.apisjwtswagger.DTO;

import org.springframework.stereotype.Component;

import java.util.List;

//Creamos un estándar para las respuestas Page<T>
public record PagedResponse<T>(
        List<T> data,
        int page,
        int size,
        long totalElements,
        int totalPages
) {

}
