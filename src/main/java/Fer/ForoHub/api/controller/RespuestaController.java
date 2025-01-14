package Fer.ForoHub.api.controller;


import Fer.ForoHub.api.dto.CrearRespuesta;
import Fer.ForoHub.api.dto.RespuestaRespuesta;
import Fer.ForoHub.api.entity.Respuesta;
import Fer.ForoHub.api.repository.RespuestaRepositorio;
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
@RequestMapping("respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepositorio respuestaRepository;

    @PostMapping
    public ResponseEntity<RespuestaRespuesta> registrarRespuesta (@RequestBody @Valid CrearRespuesta crearRespuesta,
                                                                  UriComponentsBuilder uriComponentsBuilder){

        Respuesta respuesta = respuestaRepository.save(new Respuesta(crearRespuesta));

        RespuestaRespuesta respuestaRespuesta = new RespuestaRespuesta(
                respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico(),
                respuesta.getFechaDeCreacion(), respuesta.getAutor(), respuesta.getSolucion()
        );


        URI url = uriComponentsBuilder.path("/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaRespuesta);
    }

}
