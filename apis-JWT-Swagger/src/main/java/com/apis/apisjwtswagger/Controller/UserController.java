package com.apis.apisjwtswagger.Controller;

import com.apis.apisjwtswagger.DTO.PagedResponse;
import com.apis.apisjwtswagger.DTO.Users.UserCreateDTO;
import com.apis.apisjwtswagger.DTO.Users.UserListDTO;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import com.apis.apisjwtswagger.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private UserMapStruct mapper;

    @GetMapping
    public PagedResponse<UserListDTO> getUsers(@PageableDefault(size = 5, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<UsersEntity> page = userService.showAllUsers(pageable);
        Page<UserListDTO> pageDTO = page.map(mapper::toUserListDTO);

        return new PagedResponse<>(
                pageDTO.getContent(),
                pageDTO.getTotalPages(),
                pageDTO.getSize(),
                pageDTO.getTotalElements(),
                pageDTO.getTotalPages()
        );
    }



    @GetMapping("/{id}")
    public UserListDTO getUser(@PathVariable Long id) {
        UsersEntity user = userService.searchById(id);
        return mapper.toUserListDTO(user);
    }

    @PostMapping ("/add")
    public ResponseEntity<UsersEntity> addUser(@Valid @RequestBody UserCreateDTO user) {
        UsersEntity userEntity = mapper.toUsersEntity(user);
        userService.addUser(userEntity);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<UsersEntity> editUser(@PathVariable Long id, @Valid @RequestBody UserCreateDTO user) {
            userService.updateUser(user, id);
            return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsersEntity> addUser(@PathVariable Long id) {
        userService.deleteUser(userService.searchById(id));
        return ResponseEntity.ok().build();
    }



}
