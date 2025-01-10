package Fer.ForoHub.api.service;

public record DatosRegistroTopicos(

        String titulo,

        String mensaje,

        String fechaDeCreacion,

        Boolean estadoDelTopico,

        String autor,

        String curso,

        String respuestas
) {


}
