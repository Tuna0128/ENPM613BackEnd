package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnrollRepository extends JpaRepository<EnrollmentEntity, UUID> {
	List<EnrollmentEntity> findAll();
    EnrollmentEntity findByEnrollId(UUID enrollId);
    EnrollmentEntity findByStudentIdAndBookId(UUID studentId, UUID bookId);
    EnrollmentEntity save(EnrollmentEntity enrollEntity);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="DELETE FROM enroll WHERE student_id = :studentId and book_id = :bookId", nativeQuery=true)
    void deleteByStudentIdAndBookId(UUID studentId, UUID bookId);
    Long countByBookId(UUID bookId);
}