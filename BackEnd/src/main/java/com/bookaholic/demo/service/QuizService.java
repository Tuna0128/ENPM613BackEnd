package com.bookaholic.demo.service;

import com.bookaholic.demo.entity.QuestionEntity;
import com.bookaholic.demo.entity.QuizEntity;
import com.bookaholic.demo.entity.StudentAnswerEntity;

import java.util.List;
import java.util.UUID;

public interface QuizService {
    List<QuizEntity> queryQuizzesByBookId(UUID bookId);
    Boolean saveQuiz(QuizEntity quizEntity);
    Boolean saveQuestion(QuestionEntity questionEntity);
    Boolean saveStudentAnswer(StudentAnswerEntity studentAnswerEntity);
}
