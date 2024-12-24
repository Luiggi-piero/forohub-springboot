package com.kronos.forohub.service;

import com.kronos.forohub.dto.TopicRegister;
import com.kronos.forohub.dto.TopicResponse;
import com.kronos.forohub.model.Topic;

public interface ITopicService {
    TopicResponse createTopic(TopicRegister topic);
}
