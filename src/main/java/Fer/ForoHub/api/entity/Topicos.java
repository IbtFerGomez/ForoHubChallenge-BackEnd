package Fer.ForoHub.api.entity;

import Fer.ForoHub.api.dto.CrearTopico;
import Fer.ForoHub.api.dto.DatosActualizarTopico;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "topicos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"titulo", "mensaje"})
})
@Setter
//@Getter
//@NoArgsConstructor
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
    @Column(name = "estadoDelTopico")
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

    public void actualizarTopicos(DatosActualizarTopico datosActualizarTopico) {
       if (datosActualizarTopico.id() != null){
           this.id = datosActualizarTopico.id();
       }
        if (datosActualizarTopico.titulo() != null){
            this.titulo = datosActualizarTopico.titulo();
        }if (datosActualizarTopico.mensaje() != null){
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.estadoDelTopico() != null){
            this.estadoDelTopico = datosActualizarTopico.estadoDelTopico();
        }
        if (datosActualizarTopico.autor() != null){
            this.autor = datosActualizarTopico.autor();
        }
        if (datosActualizarTopico.curso() != null){
            this.curso = datosActualizarTopico.curso();
        }
        if (datosActualizarTopico.respuesta() != null){
            this.respuesta = datosActualizarTopico.respuesta();
        }
    }
    public void desactivarTopico() {
        this.estadoDelTopico = false;
    }

    public void activarTopico() {
        this.estadoDelTopico = true;
    }
    public Topicos() {}

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
