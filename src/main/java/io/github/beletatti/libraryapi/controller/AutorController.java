package io.github.beletatti.libraryapi.controller;

import io.github.beletatti.libraryapi.controller.dto.AutorDTO;
import io.github.beletatti.libraryapi.model.Autor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autores")
// http://localhost:8080/autores
public class AutorController {

    @PostMapping
    public ResponseEntity salvar(@RequestBody AutorDTO autor){

        return new ResponseEntity("Autor Salvo com sucesso!" + autor, HttpStatus.CREATED);

    }
}
