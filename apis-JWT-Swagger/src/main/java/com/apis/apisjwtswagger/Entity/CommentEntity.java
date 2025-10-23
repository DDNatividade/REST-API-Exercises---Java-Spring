package com.apis.apisjwtswagger.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="TEXT")
    @Max(120)
    private String content;

    @NotNull
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_comments")
    private UsersEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "og_post"
    )
    private PostsEntity comment_in_post;
}
