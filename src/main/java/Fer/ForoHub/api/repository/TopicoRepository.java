package Fer.ForoHub.api.repository;

import Fer.ForoHub.api.model.Topicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository <Topicos, Long> {
}
