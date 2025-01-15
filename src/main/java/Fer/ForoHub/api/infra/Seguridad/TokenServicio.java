package Fer.ForoHub.api.infra.Seguridad;


import Fer.ForoHub.api.entity.UsuarioAutentificacion;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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


    //+++++++++++++++++++++++generar datos de validacion de token++++++++++++++++++++++++++++++++++++
    public String generarToken (UsuarioAutentificacion usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("forohub")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("token No generado");
            // Invalid Signing configuration / Couldn't convert Claims.
        }


    }

    //+++++++++++++++++++++++obtener datos de validacion de token++++++++++++++++++++++++++++++++++++
    public String getSubject(String token) {
        /*if (token == null) {
            throw new RuntimeException();
        }*/
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // validando firma
            verifier = JWT.require(algorithm)
                    .withIssuer("forohub")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error al crear el token: " + exception.getMessage(), exception);
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
