package com.kronos.forohub.service;

import com.kronos.forohub.dto.TopicListItem;
import com.kronos.forohub.dto.TopicRegister;
import com.kronos.forohub.dto.TopicResponse;
import com.kronos.forohub.dto.TopicUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITopicService {
    TopicResponse createTopic(TopicRegister topic);

    Page<TopicListItem> getTopics(Pageable pagination);

    Page<TopicListItem> findByCourse(Pageable pagination, String nameCourse);

    Page<TopicListItem> findByYear(Pageable pagination, Integer year);

    Page<TopicListItem> findByCourseAndYear(Pageable pagination,String course, Integer year);

    TopicListItem getTopicById(Long id);

    TopicListItem updateTopic(Long id, TopicUpdate topicUpdate);
}
