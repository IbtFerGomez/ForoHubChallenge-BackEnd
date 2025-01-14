package Fer.ForoHub.api.infra.Seguridad;

import Fer.ForoHub.api.repository.UsuarioAutentificacionRepositoro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicioDeAutentificacion implements UserDetailsService {

    @Autowired
    private UsuarioAutentificacionRepositoro usuarioAutentificacionRepositoro;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioAutentificacionRepositoro.findByLogin(username);
    }
}
