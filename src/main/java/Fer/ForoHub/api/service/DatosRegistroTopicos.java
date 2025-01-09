package Fer.ForoHub.api.service;

import Fer.ForoHub.api.model.Curso;
import Fer.ForoHub.api.model.Respuesta;
import Fer.ForoHub.api.model.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.awt.image.RescaleOp;
import java.util.Date;

public record DatosRegistroTopicos(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String fechaDeCreacion,
        @NotBlank
        Boolean estadoDelTopico,
        @NotBlank
        Usuario autor,
        @NotBlank
        Curso curso,
        @NotBlank
        Respuesta respuestas
) {


}
