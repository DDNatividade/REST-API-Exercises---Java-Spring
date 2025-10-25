package com.apis.apisjwtswagger.Service.Impl;

import com.apis.apisjwtswagger.DTO.Users.UserCreateDTO;
import com.apis.apisjwtswagger.Entity.RoleEntity;
import com.apis.apisjwtswagger.Entity.RoleEnum;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import com.apis.apisjwtswagger.Exceptions.UserNotFoundException;
import com.apis.apisjwtswagger.Repository.RoleRepository;
import com.apis.apisjwtswagger.Repository.UsersRepository;
import com.apis.apisjwtswagger.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsersServiceImpl implements UserService {

    @Autowired
    UsersRepository repository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public Page<UsersEntity> showAllUsers(Pageable pageable) {
        return repository.showAll(pageable)
                .orElseThrow(() -> new UserNotFoundException("There are not users yet"));
    }

    @Override
    public Page<UsersEntity> findByRole(RoleEnum role, Pageable pageable)
   {
        return repository.showByRole(role, pageable)
                .orElseThrow(() -> new UserNotFoundException("There are no users with that role yet."));
    }

    @Override
    public UsersEntity findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("There are no users with this email address."));
    }

    @Override
    public void addUser(UsersEntity user) {
        repository.save(user);
    }

    @Override
    public void deleteUser(UsersEntity user) {
        repository.delete(user);

    }

    @Override
    public UsersEntity searchById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new UserNotFoundException("There are not users with this id"));
    }

    @Override
    public void updateUser( UserCreateDTO userDTO,Long id) {
        UsersEntity userEntity = repository.findById(id).orElseThrow(()
                -> new UserNotFoundException("There are not users with this id"));
        RoleEntity roleEntity = roleRepository.findByIdRole(userDTO.getUser_role())
                .orElseThrow(() -> new UserNotFoundException("The given role does not exist"));
        userEntity.setRole(roleEntity);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());

        repository.save(userEntity);
    }
}
