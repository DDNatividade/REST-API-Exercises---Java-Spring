package com.apis.apisjwtswagger.DTO;


import com.apis.apisjwtswagger.Entity.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class PostsDTO {
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private UsersEntity users;


}
