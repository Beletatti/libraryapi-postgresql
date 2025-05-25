package io.github.beletatti.libraryapi.controller;

import io.github.beletatti.libraryapi.controller.dto.AutorDTO;
import io.github.beletatti.libraryapi.model.Autor;
import io.github.beletatti.libraryapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
// http://localhost:8080/autores
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor) {
        Autor autorEntidade = autor.mapearParaAutor();
        service.salvar(autorEntidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorEntidade.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {

        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(autor.getId(), autor.getName(), autor.getDataNascimento(), autor.getNacionalidade());

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade){
        List<Autor> resultado = service.pesquisa(name, nacionalidade);
        List<AutorDTO> lista = resultado.stream().map(autor -> new
                AutorDTO(autor.getId(),
                autor.getName(),
                autor.getDataNascimento(),
                autor.getNacionalidade())).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") String id, @RequestBody AutorDTO dto){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var autor = autorOptional.get();
        autor.setName(dto.nome());
        autor.setDataNascimento(dto.dataNascimento());
        autor.setNacionalidade(dto.nacionalidade());

        service.atualizar(autor);

        return ResponseEntity.noContent().build();
    }
}


