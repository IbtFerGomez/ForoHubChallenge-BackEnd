package Fer.ForoHub.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
