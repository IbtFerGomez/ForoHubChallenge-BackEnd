package Fer.ForoHub.api.repository;

import Fer.ForoHub.api.entity.UsuarioAutentificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioAutentificacionRepositoro extends JpaRepository<UsuarioAutentificacion, Long> {

    UserDetails findByLogin(String username);
}
