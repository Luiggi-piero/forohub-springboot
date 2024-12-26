package com.kronos.forohub.dto;

import com.kronos.forohub.model.Topic;

import java.time.LocalDateTime;

public record TopicListItem(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        Boolean status,
        String author,
        String course
) {
    public TopicListItem(Topic topic) {
        this(
            topic.getId(),
            topic.getTitle(),
            topic.getMessage(),
            topic.getCreationDate(),
            topic.getStatus(),
            topic.getAuthor(),
            topic.getCourse()
        );
    }
}
