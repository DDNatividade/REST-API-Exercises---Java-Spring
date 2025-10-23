package com.apis.apisjwtswagger.Service;

import com.apis.apisjwtswagger.Entity.CommentEntity;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface CommentService {
    Page<CommentEntity> findAll(Pageable pageable);
    Page<CommentEntity> findByAuthor(UsersEntity author, Pageable pageable);
    void addComment(CommentEntity comment);
    void deleteComment(CommentEntity comment);
    Page<CommentEntity> findByCreatedAtBetween(LocalDateTime createdAt,UsersEntity author, Pageable pageable);


}
