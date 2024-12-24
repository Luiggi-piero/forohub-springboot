package com.kronos.forohub.model;

import com.kronos.forohub.dto.TopicRegister;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity(name = "Topic")
@Table(name = "topics")
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime creationDate;
    private Boolean status;
    private String author;
    private String course;

    public Topic() {
    }

    public Topic(TopicRegister topicRegister) {
        this.author = topicRegister.idUser();
        this.message = topicRegister.message();
        this.course = topicRegister.nameCourse();
        this.title = topicRegister.title();
        this.creationDate = LocalDateTime.now();
        this.status = true;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getAuthor() {
        return author;
    }

    public String getCourse() {
        return course;
    }
}
