package com.apis.apisjwtswagger.DTO.Posts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostContentUpdateDTO {

    private String content;
}
