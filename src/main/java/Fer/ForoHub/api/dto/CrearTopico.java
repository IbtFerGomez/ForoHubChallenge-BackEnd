package Fer.ForoHub.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Boolean estadoDelTopico,
        @NotBlank
        String autor,
        @NotBlank
        String curso,
        @NotBlank
        String respuesta
){}
