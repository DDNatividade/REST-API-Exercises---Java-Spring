package com.apis.apisjwtswagger.DTO;

import com.apis.apisjwtswagger.Entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
    private Long id;
    private String email;
    private RoleEnum role;

}
