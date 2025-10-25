package com.apis.apisjwtswagger.Repository;

import com.apis.apisjwtswagger.Entity.RoleEntity;
import com.apis.apisjwtswagger.Entity.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    @Query(value="SELECT r FROM RoleEntity r WHERE r.role=?1 ")
    Optional<RoleEntity> findByRole(RoleEnum role);

    @Query(value = "Select r FROM RoleEntity r WHERE r.idRole=?1")
    Optional<RoleEntity> findByIdRole(Long idRole);
}
