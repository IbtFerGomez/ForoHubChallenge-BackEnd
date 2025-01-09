package Fer.ForoHub.api.service;

import Fer.ForoHub.api.model.Topicos;
import Fer.ForoHub.api.model.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record DatosRegistroRespuesta(
        @NotBlank
        String Mensaje,
        @NotBlank
        Topicos topicos,
        @NotBlank
        Date fechaCreacion,
        @NotBlank
        Usuario autor,
        @NotBlank
        String solucion



) {
}
