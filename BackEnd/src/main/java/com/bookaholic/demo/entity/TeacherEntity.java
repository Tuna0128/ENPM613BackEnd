package com.bookaholic.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "teacher")
public class TeacherEntity {
    @Id
    @Column(name="teacher_id")
    private UUID teacherId;

    @Column(name="name", columnDefinition = "TEXT")
    private String name;

    @Column(name="subject", columnDefinition = "TEXT")
    private String subject;

    @Column(name="teaching_time")
    private Date teachingTime;

    public TeacherEntity() {
    }

    public TeacherEntity(UUID teacherId, String name, String subject, Date teachingTime) {
        this.teacherId = teacherId;
        this.name = name;
        this.subject = subject;
        this.teachingTime = teachingTime;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(Date teachingTime) {
        this.teachingTime = teachingTime;
    }
}
