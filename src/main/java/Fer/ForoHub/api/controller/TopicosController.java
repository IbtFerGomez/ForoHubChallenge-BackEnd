package Fer.ForoHub.api.controller;


import Fer.ForoHub.api.dto.CrearTopico;
import Fer.ForoHub.api.dto.RespuestaTopico;
import Fer.ForoHub.api.entity.Topicos;
import Fer.ForoHub.api.repository.TopicoRepository;
import Fer.ForoHub.api.tatamientoErrores.RespuestaTopicoError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<Object> registrarTopico(@RequestBody @Valid CrearTopico crearTopico,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        // Verificar si ya existe un tópico con el mismo título y mensaje
        boolean existeDuplicado = topicoRepository.existsByTituloAndMensaje(crearTopico.titulo(), crearTopico.mensaje());
        if (existeDuplicado) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RespuestaTopicoError("Ya existe un tópico con el mismo título y mensaje"));

        }

        try {
            // Intentar guardar el nuevo tópico
            Topicos topicos = topicoRepository.save(new Topicos(crearTopico));
            RespuestaTopico respuestaTopico = new RespuestaTopico(
                    topicos.getId(), topicos.getTitulo(), topicos.getMensaje(), topicos.getFechaDeCreacion(),
                    topicos.getEstadoDelTopico(), topicos.getAutor(), topicos.getCurso(), topicos.getRespuesta()
            );
            // Crear URI dinamica para respuesta (1. Devuelve una respuesta `201 Create`)
            URI url = uriComponentsBuilder.path("/{id}").buildAndExpand(topicos.getId()).toUri();
            return ResponseEntity.created(url).body(respuestaTopico);
        } catch (DataIntegrityViolationException e) {
            // Capturar la excepción si ocurre una violación de la restricción única en la base de datos
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RespuestaTopicoError("Error al guardar el tópico debido a restricciones de integridad"));
        }
    }
}
