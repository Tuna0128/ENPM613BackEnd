package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, UUID> {
    TeacherEntity findByTeacherId(UUID teacherId);
    TeacherEntity save(TeacherEntity teacherEntity);
}
