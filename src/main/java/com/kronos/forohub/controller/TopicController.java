package com.kronos.forohub.controller;

import com.kronos.forohub.dto.TopicListItem;
import com.kronos.forohub.dto.TopicRegister;
import com.kronos.forohub.dto.TopicResponse;
import com.kronos.forohub.dto.TopicUpdate;
import com.kronos.forohub.service.ITopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping
    public ResponseEntity<Map<String, Object>> getTopics(@PageableDefault(size = 10, sort = "id") Pageable pagination){
        Page<TopicListItem> itemsPage = topicService.getTopics(pagination);
        Map<String, Object> response = new HashMap<>();
        response.put("content", itemsPage.getContent());
        response.put("currentPage", itemsPage.getNumber());
        response.put("totalItems", itemsPage.getTotalElements());  // total de elementos en la bd
        response.put("totalPages", itemsPage.getTotalPages());
        response.put("pageSize", itemsPage.getSize());
        response.put("hasNext", itemsPage.hasNext());
        response.put("hasPrevious", itemsPage.hasPrevious());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchByCourseOrYear")
    public ResponseEntity<Map<String, Object>> searchByCourseOrYear(
            @PageableDefault(size = 5) Pageable pagination,
            @RequestParam(required = false) String nameCourse,
            @RequestParam(required = false) Integer year
    ){

        Map<String, Object> response = new HashMap<>();
        // página vacía
        Page<TopicListItem> page = new PageImpl<>(
                Collections.emptyList(),
                PageRequest.of(0, 5), // pagina , tamanio
                0 // Total de elementos en la colección
        );

        if(nameCourse != null && year != null){
            page = topicService.findByCourseAndYear(pagination, nameCourse, year);
        }

        else if(nameCourse != null){
            page = topicService.findByCourse(pagination, nameCourse);
        }

        else if(year != null){
            page = topicService.findByYear(pagination, year);
        }

        response.put("content", page.getContent());
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());  // total de elementos en la bd
        response.put("totalPages", page.getTotalPages());
        response.put("pageSize", page.getSize());
        response.put("hasNext", page.hasNext());
        response.put("hasPrevious", page.hasPrevious());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicListItem> getTopicById(@PathVariable Long id){
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicListItem> updateTopicById(
            @PathVariable Long id,
            @RequestBody @Valid TopicUpdate data
    ){
        return ResponseEntity.ok(topicService.updateTopic(id, data));
    }
}
