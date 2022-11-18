package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.EnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnrollRepository extends JpaRepository<EnrollEntity, UUID> {
    EnrollEntity findByEnrollId(UUID enrollId);
    EnrollEntity findByStudentIdAndBookId(UUID studentId, UUID bookId);
    EnrollEntity save(EnrollEntity enrollEntity);
}
