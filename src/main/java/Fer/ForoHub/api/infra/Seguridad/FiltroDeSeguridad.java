package Fer.ForoHub.api.infra.Seguridad;

import Fer.ForoHub.api.repository.UsuarioAutentificacionRepositoro;
import com.auth0.jwt.JWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static javax.management.Query.in;

@Component
public class FiltroDeSeguridad extends OncePerRequestFilter {

    @Autowired
    private TokenServicio tokenServicio;
    @Autowired
    private UsuarioAutentificacionRepositoro usuarioAutentificacionRepositoro;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        try {
            // Obtener el token del header
            var authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Falta el token o no es válido.");
                return;
            }
            if (authHeader != null) {
                var token = authHeader.replace("Bearer ", "");
                var nombreUsuario = tokenServicio.getSubject(token);// extraer username
                System.out.println("*************************************************nombreUsuario:"+nombreUsuario);

                if (nombreUsuario != null) {
                    // Token valido
                    var usuario = usuarioAutentificacionRepositoro.findByLogin(nombreUsuario);
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                            usuario.getAuthorities()); // Forzamos un inicio de sesion
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("No autorizado: Token inválido o expirado.");
        }

        System.out.println();

    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.equals("/login"); // Excluir el endpoint /login del filtro
    }
}
