package com.apis.apisjwtswagger.DTO.Comments;

import java.time.LocalDateTime;

public record ShowCommentDTO(
        String author,
        String content,
        LocalDateTime createdAt
) {
}
