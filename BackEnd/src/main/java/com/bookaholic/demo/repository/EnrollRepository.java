package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnrollRepository extends JpaRepository<EnrollmentEntity, UUID> {
	//TODO
	List<EnrollmentEntity> findAll();
	@Query(value="SELECT * FROM public.enroll WHERE \"student_id\" = :studentId", nativeQuery=true)
	List<EnrollmentEntity> findAllByStudentId(UUID studentId);
    EnrollmentEntity findByEnrollId(UUID enrollId);
    EnrollmentEntity findByStudentIdAndBookId(UUID studentId, UUID bookId);
    EnrollmentEntity save(EnrollmentEntity enrollEntity);
    //TODO
    @Query(value="DELETE FROM public.enroll WHERE \"student_id\" = :studentId and \"book_id\" = :bookId", nativeQuery=true)
    void deleteByStudentIdAndBookId(UUID studentId, UUID bookId);
}