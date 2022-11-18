package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    StudentEntity findByStudentId(UUID studentId);
    StudentEntity save(StudentEntity studentEntity);
}
