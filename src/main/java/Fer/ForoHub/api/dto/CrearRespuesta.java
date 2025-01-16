package Fer.ForoHub.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CrearRespuesta(
        @NotBlank
        String mensaje,
        @NotBlank
        String topico,
        @NotBlank
        String autor,
        @NotBlank
        String solucion
){}
