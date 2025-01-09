package Fer.ForoHub.api.controller;

import Fer.ForoHub.api.service.DatosRegistroTopicos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/topicos")
public class TopicosController {

    @PostMapping
    public void registroTopico (@RequestBody DatosRegistroTopicos datosRegistroTopicos){

        System.out.println(datosRegistroTopicos);

    }

}
