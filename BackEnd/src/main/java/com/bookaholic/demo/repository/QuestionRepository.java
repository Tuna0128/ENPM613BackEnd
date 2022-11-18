package com.bookaholic.demo.repository;

import com.bookaholic.demo.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
    QuestionEntity findByQuestionId(UUID questionId);
    List<QuestionEntity> findByQuizId(UUID quizId);
    QuestionEntity save(QuestionEntity questionEntity);
}
