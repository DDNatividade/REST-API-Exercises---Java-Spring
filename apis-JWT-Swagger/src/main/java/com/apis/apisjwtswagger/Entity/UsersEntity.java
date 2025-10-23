package com.apis.apisjwtswagger.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name="users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_role")
    private RoleEntity role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "author_post"
    )
    private List<PostsEntity> user_posts;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "author"
    )
    private List<CommentEntity> user_comments;


}
