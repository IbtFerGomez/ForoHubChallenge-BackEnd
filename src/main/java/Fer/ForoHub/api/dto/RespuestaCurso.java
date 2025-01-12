package Fer.ForoHub.api.dto;

import Fer.ForoHub.api.entity.Categoria;

import java.time.LocalDate;

public record RespuestaCurso(
        Long id,
        String nombre,
        Categoria categoria
) {}
