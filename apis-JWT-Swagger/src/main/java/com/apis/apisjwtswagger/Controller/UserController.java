package com.apis.apisjwtswagger.Controller;

import com.apis.apisjwtswagger.DTO.UsersDTO;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import com.apis.apisjwtswagger.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @GetMapping
    public Page<UsersDTO> getUsers(@PageableDefault(size = 5, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<UsersEntity> page = userService.showAllUsers(pageable);
        return page.map(userMapper::toUsersDTO);
    }




}
