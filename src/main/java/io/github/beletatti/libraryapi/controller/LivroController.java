package io.github.beletatti.libraryapi.controller;

import io.github.beletatti.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.beletatti.libraryapi.controller.dto.ErroResposta;
import io.github.beletatti.libraryapi.exceptions.RegistroDuplicado;
import io.github.beletatti.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        try {
            return ResponseEntity.ok(dto);
        } catch (RegistroDuplicado e){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

    public LivroController(LivroService service) {
        this.service = service;
    }
}
