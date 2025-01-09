package Fer.ForoHub.api.model;

import Fer.ForoHub.api.service.DatosRegistroTopicos;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Embedded
    private Usuario autor;
//    @Embedded
//    private Curso curso;
//    @Embedded
//    private Respuesta respuesta;
    private String fechaDeCreacion;
    private Boolean estadoDelTopico;


    /*public Topico(DatosRegistroTopicos datosRegistroTopicos) {
        this.estadoDelTopico = true;
        this.titulo = datosRegistroTopicos.titulo();
        this.mensaje = datosRegistroTopicos.mensaje();
        this.autor = datosRegistroTopicos.autor();
        this.curso = datosRegistroTopicos.curso();
        this.respuestas = datosRegistroTopicos.respuestas();
        this.fechaDeCreacion = datosRegistroTopicos.fechaDeCreacion();

    }*/
}