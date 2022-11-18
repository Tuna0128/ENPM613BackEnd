package com.bookaholic.demo.service.Impl;

import com.bookaholic.demo.entity.StudentEntity;
import com.bookaholic.demo.entity.TeacherEntity;
import com.bookaholic.demo.entity.UserEntity;
import com.bookaholic.demo.repository.StudentRepository;
import com.bookaholic.demo.repository.TeacherRepository;
import com.bookaholic.demo.repository.UserRepository;
import com.bookaholic.demo.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public AccountServiceImpl(UserRepository userRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserEntity queryUserByUserId(UUID userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public StudentEntity queryStudentById(UUID userId) {
        return studentRepository.findByStudentId(userId);
    }

    @Override
    public TeacherEntity queryTeacherById(UUID userId) {
        return teacherRepository.findByTeacherId(userId);
    }

    @Override
    public UserEntity queryUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean saveStudent(UserEntity userEntity, StudentEntity studentEntity) {
        if (userEntity.getUserId() != studentEntity.getStudentId()){
            return false;
        }
        UserEntity savedUser = userRepository.save(userEntity);
        studentEntity.setStudentId(savedUser.getUserId());
        studentRepository.save(studentEntity);
        return true;
    }

    @Override
    public Boolean saveTeacher(UserEntity userEntity, TeacherEntity teacherEntity) {
        if (userEntity.getUserId() != teacherEntity.getTeacherId()){
            return false;
        }
        UserEntity savedUser = userRepository.save(userEntity);
        teacherEntity.setTeacherId(savedUser.getUserId());
        teacherRepository.save(teacherEntity);
        return true;
    }

    @Override
    public Boolean saveUser(UserEntity userEntity) {
        if(userEntity == null){
            return false;
        }
        userRepository.save(userEntity);
        return true;
    }
}
