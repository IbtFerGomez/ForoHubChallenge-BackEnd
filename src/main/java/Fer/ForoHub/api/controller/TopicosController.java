package Fer.ForoHub.api.controller;


import Fer.ForoHub.api.dto.CrearTopico;
import Fer.ForoHub.api.dto.DatosActualizarTopico;
import Fer.ForoHub.api.dto.ListaTopicoRegistrados;
import Fer.ForoHub.api.dto.RespuestaTopico;
import Fer.ForoHub.api.entity.Topicos;
import Fer.ForoHub.api.infra.tratamientoErrores.RespuestaTopicoError;
import Fer.ForoHub.api.repository.TopicoRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("topicos")
public class TopicosController {

    @Autowired
    private TopicoRepositorio topicoRepository;

    //********************REGISTRAR TOPICO***************************//

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

    //********************LISTA TODOS LOS TOPICO***************************//
    //Buscar todos los topicos
    @GetMapping ("/Todos")
    public Page<ListaTopicoRegistrados>listaTodosTopicosRegistrados (@PageableDefault(size = 10, sort = "fechaDeCreacion", direction = Sort.Direction.ASC) Pageable paginacion){
        return topicoRepository.findAll(paginacion).map(ListaTopicoRegistrados::new);
    }

    //Buscar los topicos solo activos
    @GetMapping ("/Activos")
    public List<ListaTopicoRegistrados> listaTopicosActivosRegistrados() {
        List<Topicos> activos = topicoRepository.findByEstadoDelTopicoTrue();
        System.out.println("Tópicos activos: " + activos.size());
        return activos.stream()
                .map(ListaTopicoRegistrados::new)
                .toList();
    }

    //********************BUSCAR TOPICO POR CURSO Y AÑO***************************//

    // Buscar tópicos por nombre del curso y año de creación
    @GetMapping("/Buscar")
    public List<ListaTopicoRegistrados>
    buscarPorCursoYAnio (@RequestParam(required = false) String curso,
                         @RequestParam(required = false) int anio) {

        List<Topicos> topicos;
        if (curso != null && anio != 0) { // Ambos parámetros están presentes
            topicos = topicoRepository.findByCursoAndAnio(curso, anio);
        } else if (curso != null) { // Solo curso
            topicos = topicoRepository.findByCurso(curso); // Implementa este repositorio
        } else if (anio != 0) { // Solo año
            topicos = topicoRepository.findByAnio(anio); // Implementa este repositorio
        } else { // Ningún parámetro
            topicos = topicoRepository.findAll(); // Devuelve
        }

        return topicos.stream()
                .map(ListaTopicoRegistrados::new)
                .toList();
    }

    //********************DETALLE DE TOPICO***************************//
    //Detalle de tópicos
    @GetMapping("/{id}")
    public ResponseEntity<?> listarDetalleTopico(@PathVariable Long id) {
// Validar que el ID sea positivo
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El ID debe ser un valor positivo.");
        }

// Consultar por el tópico utilizando el repositorio
        var topicoOptional = topicoRepository.findById(id);

// Si no se encuentra, devolver un error 404
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tópico con ID " + id + " no encontrado.");
        }

// Mapear el tópico encontrado a un DTO y devolver la respuesta
        Topicos topico = topicoOptional.get();
        var respuestaTopico = new RespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getEstadoDelTopico(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getRespuesta()
        );

        return ResponseEntity.ok(respuestaTopico);
    }

    //*******************ACTUALIZAR TOPICO***************************//

    @PutMapping ("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico (@PathVariable Long id,
                                            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        // Verificar que el ID en la URI coincida con el enviado en los datos
        if (!id.equals(datosActualizarTopico.id())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RespuestaTopicoError("El ID proporcionado en la URI no coincide con el ID en los datos."));
        }

        boolean existeDuplicado = topicoRepository.existsByTituloAndMensaje(datosActualizarTopico.titulo(),
                datosActualizarTopico.mensaje());

        if (existeDuplicado) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RespuestaTopicoError("Ya existe un tópico con el mismo título y mensaje"));
        }

        try {
            Topicos topicos = topicoRepository.getReferenceById(id);
            topicos.actualizarTopicos(datosActualizarTopico);
            RespuestaTopico respuesta = new RespuestaTopico(
                    topicos.getId(),
                    topicos.getTitulo(),
                    topicos.getMensaje(),
                    topicos.getFechaDeCreacion(),
                    topicos.getEstadoDelTopico(),
                    topicos.getAutor(),
                    topicos.getCurso(),
                    topicos.getRespuesta()
            );

            return ResponseEntity.ok(respuesta);

        } catch (DataIntegrityViolationException e) {
// Capturar la excepción si ocurre una violación de la restricción única en la base de datos
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RespuestaTopicoError("Error al guardar el tópico debido a restricciones de integridad"));
        }
    }
    //********************DELETE TOPICO***************************//

    @DeleteMapping("Borrar/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //*******************DeSACTIVAR TOPICO***************************//

    @DeleteMapping("Desactivar/{id}")
    @Transactional
    public ResponseEntity desactivarTopico(@PathVariable Long id) {
        Topicos topicos = topicoRepository.getReferenceById(id);
        topicos.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
    //********************ACTIVAR TOPICO***************************//

    @DeleteMapping("Activar/{id}")
    @Transactional
    public ResponseEntity activarTopico(@PathVariable Long id) {
        Topicos topicos = topicoRepository.getReferenceById(id);
        topicos.activarTopico();
        return ResponseEntity.noContent().build();
    }

}

