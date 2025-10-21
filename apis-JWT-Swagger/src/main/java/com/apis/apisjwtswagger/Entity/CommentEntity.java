package com.apis.apisjwtswagger.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name="user_comments")
    private UsersEntity author;

    @ManyToOne
    @JoinColumn(name = "posts_comment")
    private PostsEntity comment_in_post;
}
