package com.apis.apisjwtswagger.DTO.Users;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UserCreateDTO{

   private String email;

   private String password;

   private Long user_role;
}