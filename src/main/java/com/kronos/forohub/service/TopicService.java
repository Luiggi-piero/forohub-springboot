package com.kronos.forohub.service;

import com.kronos.forohub.dto.TopicRegister;
import com.kronos.forohub.dto.TopicResponse;
import com.kronos.forohub.model.Topic;
import com.kronos.forohub.repository.ITopicRepository;
import com.kronos.forohub.validations.topic.create.ValidatorCreateTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService implements ITopicService {

    @Autowired
    private ITopicRepository topicRepository;

    // agrupa todas las clases que implementan la interfaz ValidatorCreateTopic y las almacena en validatorCreateTopics
    @Autowired
    private List<ValidatorCreateTopic> validatorCreateTopics;

    @Override
    public TopicResponse createTopic(TopicRegister topicRegister) {

        // validaciones
        validatorCreateTopics.forEach(v -> v.validate(topicRegister));

        Topic topic = new Topic(topicRegister);
        Topic t = topicRepository.save(topic);
        return new TopicResponse(t);
    }
}
