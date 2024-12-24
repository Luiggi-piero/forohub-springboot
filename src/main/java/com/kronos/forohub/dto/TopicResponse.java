package com.kronos.forohub.dto;

import com.kronos.forohub.model.Topic;

import java.time.LocalDateTime;

public record TopicResponse(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate
) {
    public TopicResponse(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate()
        );
    }
}
