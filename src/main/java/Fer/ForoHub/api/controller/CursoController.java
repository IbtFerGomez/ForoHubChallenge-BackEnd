package Fer.ForoHub.api.controller;

import Fer.ForoHub.api.dto.CrearCurso;
import Fer.ForoHub.api.dto.RespuestaCurso;
import Fer.ForoHub.api.entity.Curso;
import Fer.ForoHub.api.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping("cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity <RespuestaCurso> registrarCurso (@RequestBody @Valid CrearCurso crearCurso,
                                                           UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoRepository.save(new Curso(crearCurso));

        RespuestaCurso respuestaCurso = new RespuestaCurso(
                curso.getId(), curso.getNombre(),curso.getCategoria()
        );
        URI url = uriComponentsBuilder.path("/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaCurso);

    }
}
