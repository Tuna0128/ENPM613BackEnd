package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.StudentAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswerEntity, UUID> {
    StudentAnswerEntity findByStudentAnswerId(UUID studentAnswerId);
    StudentAnswerEntity save(StudentAnswerEntity studentAnswerEntity);
}
