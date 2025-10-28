package com.apis.apisjwtswagger.DTO.Users;

import com.apis.apisjwtswagger.Entity.RoleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(
        @NotEmpty
        @NotBlank
        String email,

        @NotNull
        RoleEntity role
) {
}
