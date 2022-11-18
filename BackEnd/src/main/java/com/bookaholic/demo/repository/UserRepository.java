package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUserId(UUID userId);
    UserEntity save(UserEntity userEntity);
    UserEntity findByUsername(String username);
}
