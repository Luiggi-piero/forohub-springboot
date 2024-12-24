package com.kronos.forohub.controller;

import com.kronos.forohub.dto.TopicRegister;
import com.kronos.forohub.dto.TopicResponse;
import com.kronos.forohub.service.ITopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private ITopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponse> createTopic(
            @RequestBody @Valid TopicRegister topicRegister,
            UriComponentsBuilder uriComponentsBuilder
    ){
        TopicResponse topicResponse = topicService.createTopic(topicRegister);
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topicResponse.id()).toUri();

        return ResponseEntity.created(url).body(topicResponse);
    }
}
