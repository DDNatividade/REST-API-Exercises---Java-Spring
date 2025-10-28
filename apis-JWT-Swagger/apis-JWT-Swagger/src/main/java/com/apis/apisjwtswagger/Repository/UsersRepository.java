package com.apis.apisjwtswagger.Repository;

import com.apis.apisjwtswagger.Entity.RoleEnum;
import com.apis.apisjwtswagger.Entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Long> {
    @Query(value = "SELECT u FROM UsersEntity u WHERE u.email=?1")
    Optional<UsersEntity> findByEmail(String email);

    @Query(value = "SELECT u FROM UsersEntity u WHERE u.role=?1")
    Optional<Page<UsersEntity>> showByRole(RoleEnum role, Pageable pageable);

    @Query(value = "SELECT u from UsersEntity u")
    Optional<Page<UsersEntity>> showAll(Pageable pageable);


}
