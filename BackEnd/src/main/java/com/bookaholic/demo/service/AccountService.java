package com.bookaholic.demo.service;

import com.bookaholic.demo.entity.StudentEntity;
import com.bookaholic.demo.entity.TeacherEntity;
import com.bookaholic.demo.entity.UserEntity;

import java.util.UUID;

public interface AccountService {
    UserEntity queryUserByUserId(UUID userId);
    UserEntity queryUserByUsername(String username);
    StudentEntity queryStudentById(UUID userId);
    TeacherEntity queryTeacherById(UUID userId);
    Boolean saveStudent(UserEntity userEntity, StudentEntity studentEntity);
    Boolean saveTeacher(UserEntity userEntity, TeacherEntity teacherEntity);
    Boolean saveUser(UserEntity userEntity);
    Boolean loginAuthenticationByInput(String username, String password);
}
