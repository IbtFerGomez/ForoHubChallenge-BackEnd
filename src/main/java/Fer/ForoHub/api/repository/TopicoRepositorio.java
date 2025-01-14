package Fer.ForoHub.api.repository;

import Fer.ForoHub.api.entity.Topicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepositorio extends JpaRepository <Topicos, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    List<Topicos> findByEstadoDelTopicoTrue();

    // Buscar tópicos por nombre del curso y año de creación
    @Query("SELECT t FROM Topicos t WHERE t.curso = :curso AND FUNCTION('YEAR', t.fechaDeCreacion) = :anio")
    List<Topicos> findByCursoAndAnio(@Param("curso") String curso, @Param("anio") int anio);

    List<Topicos> findByCurso(String curso);

    @Query("SELECT t FROM Topicos t WHERE YEAR(t.fechaDeCreacion) = :anio")
    List<Topicos> findByAnio(@Param("anio") Integer anio);
}




