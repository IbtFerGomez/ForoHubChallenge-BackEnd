package Fer.ForoHub.api.controller;


import Fer.ForoHub.api.dto.DatosAutentificacionUsuarios;
import Fer.ForoHub.api.entity.UsuarioAutentificacion;
import Fer.ForoHub.api.infra.Seguridad.DatosJWTtoken;
import Fer.ForoHub.api.infra.Seguridad.TokenServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AutentificacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenServicio tokenServicio;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutentificacionUsuarios
                                                    datosAutenticacionUsuario) {
        Authentication authtoken= new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.password());

        var usuarioAutenticado = authenticationManager.authenticate(authtoken);

        var JWTtoken = tokenServicio.generarToken((UsuarioAutentificacion) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }

}
