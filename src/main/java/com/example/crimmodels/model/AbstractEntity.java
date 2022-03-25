package com.example.crimmodels.model;


import com.example.crimmodels.util.DateProcessor;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public class AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateProcessor.DATE_FORMAT)
    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateProcessor.DATE_FORMAT)
    @Column(name = "modified_at", nullable = false)
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    private LocalDateTime modifiedAt;

    public AbstractEntity(){
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
