package Fer.ForoHub.api.dto;

import java.time.LocalDate;

public record RespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaDeCreacion,
        Boolean estadoDelTopico,
        String autor,
        String curso,
        String respuesta
) {
}
