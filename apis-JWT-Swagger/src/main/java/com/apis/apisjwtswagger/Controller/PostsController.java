package com.apis.apisjwtswagger.Controller;

import com.apis.apisjwtswagger.DTO.PagedResponse;
import com.apis.apisjwtswagger.DTO.Posts.PostsCreateDTO;
import com.apis.apisjwtswagger.DTO.Posts.PostsReadDTO;
import com.apis.apisjwtswagger.Entity.PostsEntity;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import com.apis.apisjwtswagger.Service.Impl.PostServiceImpl;
import com.apis.apisjwtswagger.Service.Impl.UsersServiceImpl;
import com.apis.apisjwtswagger.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/apis/posts")
public class PostsController {
    @Autowired
    PostServiceImpl Postservice;
    @Autowired
    PostsMapStruct mapper;
    @Autowired
    UsersServiceImpl UserService;
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<PagedResponse<PostsReadDTO>> getPosts(Pageable pageable) {
        Page<PostsEntity> posts =Postservice.showAll(pageable);
        Page<PostsReadDTO> postsDTOs= posts.map(mapper::toPostsDTO);
        PagedResponse<PostsReadDTO> pagedPostsDTO=
                new PagedResponse<>(
                        postsDTOs.getContent(),
                        postsDTOs.getTotalPages(),
                        postsDTOs.getSize(),
                        postsDTOs.getTotalElements(),
                        postsDTOs.getTotalPages()
                );
        return ResponseEntity.status(HttpStatus.FOUND).body(pagedPostsDTO);
    }
    @PostMapping("/users/{id}")
        public ResponseEntity<PostsCreateDTO> addPosts( @PathVariable @Valid Long id, @RequestBody PostsCreateDTO posts) {
        PostsEntity postsEntity = mapper.toPostsEntity(posts);
        postsEntity.setCreatedDate(LocalDateTime.now());
        postsEntity.setAuthor_post(UserService.searchById(id));
        Postservice.addPost(postsEntity);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<PagedResponse<PostsReadDTO>> getPosts(@PathVariable @Valid Long id, Pageable pageable) {
        UsersEntity user=UserService.searchById(id);
        Page<PostsEntity> posts=Postservice.findByAuthor(user,pageable);
        Page<PostsReadDTO> postsDTOs =posts.map(mapper::toPostsDTO);
        PagedResponse<PostsReadDTO> postsUserID= new PagedResponse<>(
                postsDTOs.getContent(),
                postsDTOs.getTotalPages(),
                postsDTOs.getSize(),
                postsDTOs.getTotalElements(),
                postsDTOs.getTotalPages()
        );
        return ResponseEntity.status(HttpStatus.FOUND).body(postsUserID);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost (@PathVariable @Valid Long id) {
        postService.deletePost(postService.findById(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<PostsEntity> updatePost(
            @PathVariable @Valid Long id,
            @RequestBody PostsCreateDTO posts)
    {
        PostsEntity postOriginal=postService.findById(id);
        postOriginal.setTitle(posts.getTitle());
        postOriginal.setContent(posts.getContent());

        postService.addPost(postOriginal);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(
            @PathVariable @Valid Long id,
            @RequestBody Map<String, Object> updates) {

        PostsEntity post = postService.findById(id);

        if (updates.containsKey("title")) {
            post.setTitle((String) updates.get("title"));
        }
        if (updates.containsKey("content")) {
            post.setContent((String) updates.get("content"));
        }

        postService.addPost(post);
        return ResponseEntity.ok().build();
    }





}
