package com.kronos.forohub.service;

import com.kronos.forohub.dto.TopicListItem;
import com.kronos.forohub.dto.TopicRegister;
import com.kronos.forohub.dto.TopicResponse;
import com.kronos.forohub.dto.TopicUpdate;
import com.kronos.forohub.model.Topic;
import com.kronos.forohub.repository.ITopicRepository;
import com.kronos.forohub.validations.topic.create.ValidatorCreateTopic;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<TopicListItem> getTopics(Pageable pagination) {
        return topicRepository.findAll(pagination).map(TopicListItem::new);
    }

    @Override
    public Page<TopicListItem> findByCourse(Pageable pagination, String nameCourse) {
        return topicRepository.findByCourseContainsIgnoreCase(pagination, nameCourse).map(TopicListItem::new);
    }

    @Override
    public Page<TopicListItem> findByYear(Pageable pagination, Integer year) {
        return topicRepository.findByYear(pagination, year).map(TopicListItem::new);
    }

    @Override
    public Page<TopicListItem> findByCourseAndYear(Pageable pagination, String course, Integer year) {
        return topicRepository.findByCourseAndYear(pagination, course, year).map(TopicListItem::new);
    }

    @Override
    public TopicListItem getTopicById(Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if(topic.isEmpty()){
            throw new EntityNotFoundException();
        }
        return new TopicListItem(topic.get());
    }

    @Override
    public TopicListItem updateTopic(Long id, TopicUpdate topicUpdate) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.update(topicUpdate);
        return new TopicListItem(topic);
    }
}
