package com.apis.apisjwtswagger.Service;

import com.apis.apisjwtswagger.Entity.CommentEntity;
import com.apis.apisjwtswagger.Entity.PostsEntity;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.stream.Stream;

// CommentService.java
public interface CommentService {
    Page<CommentEntity> findAll(Pageable pageable);
    Page<CommentEntity> findByAuthor(UsersEntity author, Pageable pageable);
    void addComment(CommentEntity comment);
    void deleteComment(CommentEntity comment);
    CommentEntity findById(Long id);
    // Corregido: ahora recibe dos fechas para el rango
    Page<CommentEntity> findByCreatedAtBetween(
            LocalDateTime startDate,
            LocalDateTime endDate,
            UsersEntity author,
            Pageable pageable);

    Page<CommentEntity> findByPostAndAuthor(PostsEntity posts, UsersEntity author, Pageable pageable);
}


