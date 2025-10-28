package com.apis.apisjwtswagger.Controller;


import com.apis.apisjwtswagger.DTO.Comments.CreateCommentDTO;
import com.apis.apisjwtswagger.DTO.Comments.ShowCommentDTO;
import com.apis.apisjwtswagger.DTO.PagedResponse;
import com.apis.apisjwtswagger.Entity.CommentEntity;
import com.apis.apisjwtswagger.Entity.PostsEntity;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import com.apis.apisjwtswagger.Service.CommentService;
import com.apis.apisjwtswagger.Service.PostService;
import com.apis.apisjwtswagger.Service.UserService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("apis/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    CommentsMapStruct mapper;

    @GetMapping
    public PagedResponse<ShowCommentDTO> showComments(Pageable pageable){
        Page<CommentEntity> commentEntities=commentService.findAll(pageable);
        Page<ShowCommentDTO> commentDTOS= commentEntities.map(mapper::toShowCommentsDTO);

        return new PagedResponse<>(
                commentDTOS.getContent(),
                commentDTOS.getTotalPages(),
                commentDTOS.getSize(),
                commentDTOS.getNumber(),
                commentDTOS.getNumberOfElements()
        );
    }

    @PostMapping("/posts/{idPost}/users/{idUser}")
    public ResponseEntity<CommentEntity> saveComment(
           @PathVariable @Valid Long idPost,
           @PathVariable @Valid Long idUser,
            @RequestBody CreateCommentDTO comment){

        PostsEntity post= postService.findById(idPost);
        UsersEntity user= userService.searchById(idUser);
        CommentEntity newComment=mapper.toCommentEntity(comment);

        newComment.setAuthor(user);
        newComment.setCreatedAt(LocalDateTime.now());
        newComment.setComment_in_post(post);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/posts/{idPost}/users/{idUser}")
    public ResponseEntity<PagedResponse<ShowCommentDTO>> getComment(
            @PathVariable @Valid Long idPost,
           @PathVariable @Valid Long idUser,
            Pageable pageable){
        PostsEntity post= postService.findById(idPost);
        UsersEntity user= userService.searchById(idUser);
        Page<CommentEntity> commentEntities = commentService.findByPostAndAuthor(post,user,pageable);
        Page<ShowCommentDTO> commentDTOS = commentEntities.map(mapper::toShowCommentsDTO);

        PagedResponse<ShowCommentDTO> commentDTOS2 = new PagedResponse<>(
                commentDTOS.getContent(),
                commentDTOS.getTotalPages(),
                commentDTOS.getSize(),
                commentDTOS.getNumber(),
                commentDTOS.getNumberOfElements()
        );

        return ResponseEntity.status(HttpStatus.OK).body(commentDTOS2);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<PagedResponse<ShowCommentDTO>> showCommentsByAuthor(@PathVariable @Valid Long id, Pageable pageable){
        UsersEntity user= userService.searchById(id);
        Page<CommentEntity> commentEntities=commentService.findByAuthor(user,pageable);
        Page<ShowCommentDTO> commentDTOS = commentEntities.map(mapper::toShowCommentsDTO);

        PagedResponse<ShowCommentDTO> commentDTOS2 = new PagedResponse<>(
               commentDTOS.getContent(),
               commentDTOS.getTotalPages(),
               commentDTOS.getSize(),
               commentDTOS.getNumber(),
               commentDTOS.getNumberOfElements()
        );

        return ResponseEntity.status(HttpStatus.OK).body(commentDTOS2);

    }

    @GetMapping("users/{id}/date")
    public ResponseEntity<PagedResponse<ShowCommentDTO>> showCommentsByDate(
            @PathVariable @Valid Long id,
            @RequestParam String date,
            Pageable pageable){
        LocalDateTime localDate = LocalDateTime.parse(date);

        UsersEntity user= userService.searchById(id);

        Page<CommentEntity> commentEntities=commentService.findByCreatedAtBetween(localDate,user,pageable);
        Page<ShowCommentDTO> commentDTOS = commentEntities.map(mapper::toShowCommentsDTO);

        PagedResponse<ShowCommentDTO> commentDTOS2 = new PagedResponse<>(
                commentDTOS.getContent(),
                commentDTOS.getTotalPages(),
                commentDTOS.getSize(),
                commentDTOS.getNumber(),
                commentDTOS.getNumberOfElements()
        );

        return ResponseEntity.status(HttpStatus.FOUND).body(commentDTOS2);
    }




}
