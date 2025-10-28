package com.apis.apisjwtswagger.DTO.Posts;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTitleUpdateDTO {

    String title;
}
