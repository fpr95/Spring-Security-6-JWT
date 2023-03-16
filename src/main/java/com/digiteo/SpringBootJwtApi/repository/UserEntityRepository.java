package com.digiteo.SpringBootJwtApi.repository;

import com.digiteo.SpringBootJwtApi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM userEntity u WHERE u.email = ?1")
    Optional<UserEntity> findByEmail(String email);
}
