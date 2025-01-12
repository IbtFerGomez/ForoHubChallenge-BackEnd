package Fer.ForoHub.api.dto;

import Fer.ForoHub.api.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearCurso(
        @NotBlank
        String nombre,
        @NotNull
        Categoria categoria
        ) {

}
