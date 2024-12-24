package com.kronos.forohub.validations.topic.create;

import com.kronos.forohub.dto.TopicRegister;
import com.kronos.forohub.repository.ITopicRepository;
import com.kronos.forohub.validations.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // componente generico en spring que se carga automaticamente
public class ValidatorTitleMessageUnique implements ValidatorCreateTopic {

    @Autowired
    private ITopicRepository topicRepository;

    public void validate(TopicRegister data){
        boolean exists = topicRepository.existsByTitleAndMessage(data.title(), data.message());
        if(exists){
            throw new ValidationException("No pueden existir 2 o más tópicos con el mismo titulo y mensaje");
        }
    }
}
