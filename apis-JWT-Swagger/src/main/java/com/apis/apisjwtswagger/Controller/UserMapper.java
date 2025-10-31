package com.apis.apisjwtswagger.Controller;

import com.apis.apisjwtswagger.DTO.Users.UserCreateDTO;
import com.apis.apisjwtswagger.DTO.Users.UserListDTO;
import com.apis.apisjwtswagger.DTO.Users.UserLoginDTO;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Component
public class UserMapper {
    @Autowired
    ModelMapper modelMapper;

    public UsersEntity toUsersEntity(UserCreateDTO users) {

        return modelMapper.map(users, UsersEntity.class);
    }

    public UserCreateDTO toUsersDTO(UsersEntity users) {
        return modelMapper.map(users, UserCreateDTO.class);
    }

    public UserListDTO toUserListDTO(UsersEntity users) {
        return modelMapper.map(users, UserListDTO.class);
    }

    public UsersEntity toUsersEntity(UserLoginDTO users){return modelMapper.map(users,UsersEntity.class);}




}
