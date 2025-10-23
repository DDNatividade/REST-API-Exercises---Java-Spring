package com.apis.apisjwtswagger.Controller;

import com.apis.apisjwtswagger.DTO.UsersDTO;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class UserMapper {
    @Autowired
    ModelMapper modelMapper;

    public UsersEntity toUsersEntity(UsersDTO users) {
        return modelMapper.map(users, UsersEntity.class);
    }

    public UsersDTO toUsersDTO(UsersEntity users) {
        return modelMapper.map(users, UsersDTO.class);
    }


}
