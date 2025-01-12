package Fer.ForoHub.api.entity;

import Fer.ForoHub.api.dto.CrearTopico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "topicos")
//@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")


public class Topicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fechaDeCreacion")
    private LocalDate fechaDeCreacion;
    private Boolean estadoDelTopico;
    private String autor;
    private String curso;
    private String respuesta;

    @PrePersist
    public void prePersist() {
        if (this.fechaDeCreacion == null) {
            this.fechaDeCreacion = LocalDate.now();  // Set current date before persisting
        }
    }

    public Topicos(CrearTopico crearTopico) {
        this.titulo = crearTopico.titulo();
        this.mensaje = crearTopico.mensaje();
        this.estadoDelTopico = crearTopico.estadoDelTopico();
        this.autor = crearTopico.autor();
        this.curso = crearTopico.curso();
        this.respuesta=crearTopico.respuesta();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public Boolean getEstadoDelTopico() {
        return estadoDelTopico;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

    public String getRespuesta() {
        return respuesta;
    }
}
