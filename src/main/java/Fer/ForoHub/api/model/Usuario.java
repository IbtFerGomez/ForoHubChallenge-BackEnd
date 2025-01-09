package Fer.ForoHub.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


//@Table(name = "usuario")
//@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = "id")
//@Embeddable
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;
//    @Embedded
//    private Perfil perfil;
}
