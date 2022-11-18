package com.bookaholic.demo.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @Column(name="student_id")
    private UUID studentId;

    public StudentEntity() {
    }

    public StudentEntity(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

}
