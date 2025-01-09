package Fer.ForoHub.api.service;

import Fer.ForoHub.api.model.Perfil;

public record DatosRegistroUsuario(
        String nombre,
        String email,
        String contraseña,
        Perfil perfiles
) {
}
