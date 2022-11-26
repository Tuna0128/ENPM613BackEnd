package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnrollRepository extends JpaRepository<EnrollmentEntity, UUID> {
    EnrollmentEntity findByEnrollId(UUID enrollId);
    EnrollmentEntity findByStudentIdAndBookId(UUID studentId, UUID bookId);
    EnrollmentEntity save(EnrollmentEntity enrollEntity);
}
