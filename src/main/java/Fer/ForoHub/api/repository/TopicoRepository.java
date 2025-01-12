package Fer.ForoHub.api.repository;

import Fer.ForoHub.api.entity.Topicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository <Topicos, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
