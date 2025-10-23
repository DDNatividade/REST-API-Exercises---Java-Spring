package com.apis.apisjwtswagger.Service;


import com.apis.apisjwtswagger.Entity.RoleEnum;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    Page<UsersEntity> showAllUsers(Pageable pageable);
    Page<UsersEntity> findByRole(RoleEnum role, Pageable pageable);
    UsersEntity  findByEmail(String email);
    void addUser(UsersEntity user);
    void deleteUser(UsersEntity user);

}
