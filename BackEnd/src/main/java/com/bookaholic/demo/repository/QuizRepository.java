package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, UUID> {
    QuizEntity findByQuizId(UUID quizId);
    List<QuizEntity> findByBookId(UUID bookId);
    QuizEntity save(QuizEntity quizEntity);
}
