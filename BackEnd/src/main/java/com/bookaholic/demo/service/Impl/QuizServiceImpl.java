package com.bookaholic.demo.service.Impl;

import com.bookaholic.demo.entity.QuestionEntity;
import com.bookaholic.demo.entity.QuizEntity;
import com.bookaholic.demo.entity.StudentAnswerEntity;
import com.bookaholic.demo.repository.*;
import com.bookaholic.demo.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class QuizServiceImpl implements QuizService {
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final StudentAnswerRepository studentAnswerRepository;

    public QuizServiceImpl(StudentRepository studentRepository, BookRepository bookRepository, QuestionRepository questionRepository, QuizRepository quizRepository, StudentAnswerRepository studentAnswerRepository) {
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
        this.studentAnswerRepository = studentAnswerRepository;
    }

    @Override
    public List<QuizEntity> queryQuizzesByBookId(UUID bookId) {
        return quizRepository.findByBookId(bookId);
    }

    @Override
    public Boolean saveQuiz(QuizEntity quizEntity) {
        if(quizEntity == null){
            return false;
        }
        if(bookRepository.findByBookId(quizEntity.getBookId()) == null){
            return false;
        }
        quizEntity.setCreatedDate(new Date());
        quizRepository.save(quizEntity);
        return true;
    }

    @Override
    public Boolean saveQuestion(QuestionEntity questionEntity) {
        if (questionEntity == null){
            return false;
        }
        if (quizRepository.findByQuizId(questionEntity.getQuizId()) == null){
            return false;
        }
        questionRepository.save(questionEntity);
        return true;
    }

    @Override
    public Boolean saveStudentAnswer(StudentAnswerEntity studentAnswerEntity) {
        if (studentAnswerEntity == null){
            return false;
        }
        if(studentRepository.findByStudentId(studentAnswerEntity.getStudentId()) == null){
            return false;
        }
        if (questionRepository.findByQuestionId(studentAnswerEntity.getQuestionId()) == null){
            return false;
        }
        studentAnswerRepository.save(studentAnswerEntity);
        return true;
    }
}
