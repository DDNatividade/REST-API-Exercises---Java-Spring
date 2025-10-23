package com.apis.apisjwtswagger.Service;

import com.apis.apisjwtswagger.Entity.CommentEntity;
import com.apis.apisjwtswagger.Entity.PostsEntity;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public Page<PostsEntity> findByAuthor(UsersEntity author, Pageable pageable);
    public Page<PostsEntity> showAll(Pageable pageable);
    public void addPost(PostsEntity post);
    public void deletePost(PostsEntity post);
    public PostsEntity findByAuthorAndTitle(UsersEntity author, String title);
}
