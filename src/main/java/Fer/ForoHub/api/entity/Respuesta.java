package Fer.ForoHub.api.entity;

import Fer.ForoHub.api.dto.CrearRespuesta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "respuestas")
//@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private String topico;
    @Column(name = "fechaDeCreacion")
    private LocalDate fechaDeCreacion;
    private String autor;
    private String solucion;

    @PrePersist
    public void prePersist() {
        if (this.fechaDeCreacion == null) {
            this.fechaDeCreacion = LocalDate.now();  // Set current date before persisting
        }
    }

   public Respuesta(CrearRespuesta crearRespuesta) {

        this.mensaje = crearRespuesta.mensaje();
        this.topico = crearRespuesta.topico();
        this.autor = crearRespuesta.autor();
        this.solucion = crearRespuesta.solucion();
    }

    public Long getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getTopico() {
        return topico;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public String getAutor() {
        return autor;
    }

    public String getSolucion() {
        return solucion;
    }
}
