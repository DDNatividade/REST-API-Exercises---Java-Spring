package com.apis.apisjwtswagger.DTO.Posts;

import com.apis.apisjwtswagger.Entity.UsersEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PostsCreateDTO {


    private String title;



    private String content;


}
