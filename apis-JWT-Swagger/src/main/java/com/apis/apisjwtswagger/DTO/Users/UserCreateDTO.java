package com.apis.apisjwtswagger.DTO.Users;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UserCreateDTO{

   @NotEmpty
   @NotBlank
   private String email;

   @NotEmpty
   @NotBlank
   private String password;

   @NotNull
   private Long user_role;
}