package com.apis.apisjwtswagger.DTO.Posts;

import java.time.LocalDateTime;

public record PostsReadDTO(
        String title,
        LocalDateTime createdDate,
        Long user) {
}
