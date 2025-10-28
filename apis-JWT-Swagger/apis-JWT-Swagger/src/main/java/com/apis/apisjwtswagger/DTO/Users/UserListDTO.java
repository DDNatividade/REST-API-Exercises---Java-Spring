package com.apis.apisjwtswagger.DTO.Users;

import com.apis.apisjwtswagger.Entity.RoleEnum;


public record UserListDTO(
        String email,
        RoleEnum role
) {

}
