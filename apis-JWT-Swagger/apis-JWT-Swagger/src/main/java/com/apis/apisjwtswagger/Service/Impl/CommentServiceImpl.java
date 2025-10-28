package com.apis.apisjwtswagger.Service.Impl;

import com.apis.apisjwtswagger.Entity.CommentEntity;
import com.apis.apisjwtswagger.Entity.PostsEntity;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import com.apis.apisjwtswagger.Exceptions.NoCommentFoundException;
import com.apis.apisjwtswagger.Repository.CommentRepository;
import com.apis.apisjwtswagger.Service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository repository;

    @Override
    public Page<CommentEntity> findAll(Pageable pageable) {
        return repository.showAll(pageable)
                .orElseThrow(() -> new NoCommentFoundException("No comments have made yet"));
    }

    @Override
    public Page<CommentEntity> findByAuthor(UsersEntity author, Pageable pageable) {
        return repository.findByAuthor(author, pageable)
                .orElseThrow(() -> new NoCommentFoundException("No comments have been made by this user."));
    }

    @Override
    public void addComment(CommentEntity comment) {
        repository.save(comment);
    }

    @Override
    public void deleteComment(CommentEntity comment) {
        repository.delete(comment);
    }


    @Override
    public Page<CommentEntity> findByCreatedAtBetween(LocalDateTime startOfTheDay,
                                                      UsersEntity author,
                                                      Pageable pageable) {
        return repository.findByCreatedAtBetween(startOfTheDay,author,pageable)
                .orElseThrow(() -> new NoCommentFoundException("No comments have been found." ));
    }

    @Override
    public Page<CommentEntity> findByPostAndAuthor(PostsEntity posts, UsersEntity author, Pageable pageable) {
        return repository.findByPostAndAuthor(posts,author,pageable).orElseThrow(() -> new NoCommentFoundException("No comments have been found."));
    }




}
