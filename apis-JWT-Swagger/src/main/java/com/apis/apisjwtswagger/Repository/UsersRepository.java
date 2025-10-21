package com.apis.apisjwtswagger.Repository;

import com.apis.apisjwtswagger.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Long> {

}
