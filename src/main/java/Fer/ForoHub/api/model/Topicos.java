package Fer.ForoHub.api.model;

import Fer.ForoHub.api.service.DatosRegistroTopicos;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private String autor;
    private String curso;
    private String respuesta;
    private LocalDate fechaDeCreacion;
    private Boolean estadoDelTopico;
}
