package com.green.springjpa.student.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class StudentRes {
    private Long studentId;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public StudentRes(Long studentId, String name, LocalDateTime createdAt) {
        this.studentId = studentId;
        this.name = name;
        this.createdAt = createdAt;
    }
}
