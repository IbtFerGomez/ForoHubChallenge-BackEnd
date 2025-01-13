package Fer.ForoHub.api.dto;


import Fer.ForoHub.api.entity.Topicos;

public record ListaTopicoRegistrados(

        String titulo,
        String mensaje,
        String fechaDeCreacion,
        String estadoDelTopico,
        String autor,
        String curso
) {

    public ListaTopicoRegistrados (Topicos topicos){
        this (topicos.getTitulo(), topicos.getMensaje(), topicos.getFechaDeCreacion().toString(),
                topicos.getEstadoDelTopico() != null
                        ? (topicos.getEstadoDelTopico() ? "Activo" : "Inactivo")
                        : "Inactivo",  topicos.getAutor(), topicos.getCurso());

    }

}
