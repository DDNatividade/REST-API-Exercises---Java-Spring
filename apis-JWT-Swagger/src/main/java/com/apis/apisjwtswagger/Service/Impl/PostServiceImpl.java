package com.apis.apisjwtswagger.Service.Impl;

import com.apis.apisjwtswagger.Entity.PostsEntity;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import com.apis.apisjwtswagger.Exceptions.NoPostsFoundException;
import com.apis.apisjwtswagger.Repository.PostsRepository;
import com.apis.apisjwtswagger.Service.PostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public Page<PostsEntity> findByAuthor(UsersEntity author, Pageable pageable) {
        return postsRepository
                .findAllByUser_posts(author,pageable)
                .orElseThrow(()-> new NoPostsFoundException("There are no posts related to this user."));
    }

    @Override
    public Page<PostsEntity> showAll(Pageable pageable) {

        return postsRepository.showAll(pageable)
                .orElseThrow(() -> new NoPostsFoundException("There are no posts yet"));
    }

    @Override
    public void addPost(PostsEntity post) {
         postsRepository.save(post);
    }

    @Override
    public void deletePost(PostsEntity post) {
        postsRepository.delete(post);
    }

    @Override
    public PostsEntity findByAuthorAndTitle(UsersEntity author, String title) {
        return postsRepository
                .findByPostAndAuthor(author, title)
                .orElseThrow(() -> new NoPostsFoundException("No posts found."));
    }
}
