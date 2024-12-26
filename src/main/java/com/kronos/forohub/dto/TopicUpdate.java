package com.kronos.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record TopicUpdate(
        @NotNull(message = "El id es necesario")
        @Positive(message = "El id debe ser un número positivo")
        Long id,
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
