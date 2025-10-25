package com.apis.apisjwtswagger.Controller;
import com.apis.apisjwtswagger.DTO.Users.UserCreateDTO;
import com.apis.apisjwtswagger.DTO.Users.UserListDTO;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapStruct {

    @Mapping(target = "role", source = "role.role")
    UserListDTO toUserListDTO(UsersEntity entity);

    UserCreateDTO toUsersDTO(UsersEntity entity);

    UsersEntity toUsersEntity(UserCreateDTO dto);

}