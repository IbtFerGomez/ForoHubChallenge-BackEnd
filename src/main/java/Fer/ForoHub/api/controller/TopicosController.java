package Fer.ForoHub.api.controller;


import Fer.ForoHub.api.dto.CrearTopico;
import Fer.ForoHub.api.dto.RespuestaTopico;
import Fer.ForoHub.api.entity.Topicos;
import Fer.ForoHub.api.repository.TopicoRepository;
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
@RequestMapping("topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<RespuestaTopico> registrarTopico (@RequestBody @Valid CrearTopico crearTopico,
                                                            UriComponentsBuilder uriComponentsBuilder){

        Topicos topicos = topicoRepository.save(new Topicos(crearTopico));
        RespuestaTopico respuestaTopico = new RespuestaTopico(
                topicos.getId(), topicos.getTitulo(), topicos.getMensaje(), topicos.getFechaDeCreacion(),
                topicos.getEstadoDelTopico(), topicos.getAutor(), topicos.getCurso(), topicos.getRespuesta()
        );

        URI url = uriComponentsBuilder.path("/{id}").buildAndExpand(topicos.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaTopico);
    }

}
