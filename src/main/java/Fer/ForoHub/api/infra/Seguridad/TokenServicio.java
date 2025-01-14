package Fer.ForoHub.api.infra.Seguridad;


import Fer.ForoHub.api.entity.UsuarioAutentificacion;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServicio {
    
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken (UsuarioAutentificacion usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
           return JWT.create()
                    .withIssuer("forohub")
                   .withSubject(usuario.getLogin())
                   .withClaim("id", usuario.getId())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
            // Invalid Signing configuration / Couldn't convert Claims.
        }
    }
    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
