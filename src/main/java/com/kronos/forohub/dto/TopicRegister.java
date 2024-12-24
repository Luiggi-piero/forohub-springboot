package com.kronos.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicRegister(
        @NotBlank(message = "Id del autor/usuario es necesario")
        String idUser,
        @NotBlank(message = "El mensaje es necesario")
        String message,
        @NotBlank(message = "El nombre del curso es necesario")
        String nameCourse,
        @NotBlank(message = "El titulo es necesario")
        String title
) {
}
