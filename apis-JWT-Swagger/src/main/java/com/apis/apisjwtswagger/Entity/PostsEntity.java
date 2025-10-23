package com.apis.apisjwtswagger.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name="posts")
public class PostsEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @Column(columnDefinition = "TEXT")
    @Max(12000)
    private String content;

    @NotNull
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name="user_posts")
    private UsersEntity author_post;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy="comment_in_post")
    List<CommentEntity> posts_in_comment;


}
