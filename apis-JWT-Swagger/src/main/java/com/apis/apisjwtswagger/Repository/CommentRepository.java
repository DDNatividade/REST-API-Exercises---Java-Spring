package com.apis.apisjwtswagger.Repository;

import com.apis.apisjwtswagger.Entity.CommentEntity;
import com.apis.apisjwtswagger.Entity.PostsEntity;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query(value = "SELECT c FROM CommentEntity c ")
    Optional<Page<CommentEntity>> showAll(Pageable pageable);

    @Query(value = "SELECT c FROM CommentEntity c WHERE c.comment_in_post=?1")
    Optional<Page<CommentEntity>>  findByPost(PostsEntity post, Pageable pageable);

    @Query(value = "SELECT c FROM CommentEntity c WHERE c.author=?1")
    Optional<Page<CommentEntity>>  findByAuthor(UsersEntity author, Pageable pageable);

    @Query(value ="SELECT c from CommentEntity c " +
            "WHERE c.createdAt=?1 AND c.author=?2" )
    Optional<Page<CommentEntity>>  findByCreatedAtBetween(LocalDateTime createdAt, UsersEntity author, Pageable pageable);


}
