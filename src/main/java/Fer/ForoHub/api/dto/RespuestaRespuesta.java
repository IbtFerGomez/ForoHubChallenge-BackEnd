package Fer.ForoHub.api.dto;

import java.time.LocalDate;

public record RespuestaRespuesta(
        Long id,
        String mensaje,
        String topico,
        LocalDate fechaDeCreacion,
        String autor,
        String solucion
) {}
