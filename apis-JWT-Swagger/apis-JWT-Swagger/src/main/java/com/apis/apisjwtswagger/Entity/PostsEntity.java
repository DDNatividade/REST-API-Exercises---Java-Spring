package com.apis.apisjwtswagger.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String content;

    @NotNull
    private LocalDateTime createdDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name="user_posts")
    private UsersEntity author_post;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy="comment_in_post")
    List<CommentEntity> posts_in_comment;


}
