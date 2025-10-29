package com.apis.apisjwtswagger.Repository;

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
public interface PostsRepository extends JpaRepository<PostsEntity,Long> {
    /*Lenguaje JPQL.
    PostEntity es el nombre de la entidad JPA, no el nombre de la tabla.
    p.userPosts es el nombre del campo en la entidad (no de la columna SQL).
    ?1 indica el primer parámetro del metodo*/
    @Query(value = "SELECT p FROM PostsEntity  p WHERE p.author_post =?1" )
    Optional<Page<PostsEntity>> findAllByUser_posts(UsersEntity user, Pageable pageable);

    @Query(value = "SELECT p FROM PostsEntity p WHERE p.createdDate =?1")
    Optional<Page<PostsEntity>> findAllByCreatedDate(LocalDateTime createdDate, Pageable pageable);

    @Query(value = "SELECT p FROM PostsEntity p " +
            "WHERE p.title =?1 AND p.author_post=?2")
    Optional<PostsEntity> findByPostAndAuthor(UsersEntity author, String title);

    @Query(value = "SELECT p FROM PostsEntity p")
    Optional<Page<PostsEntity>> showAll(Pageable pageable);

    @Query(value = "SELECT p FROM PostsEntity p WHERE p.id=?1")
    Optional<PostsEntity> findById(Long id);
}
