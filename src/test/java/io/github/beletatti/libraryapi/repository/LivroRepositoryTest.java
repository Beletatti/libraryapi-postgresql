package io.github.beletatti.libraryapi.repository;

import io.github.beletatti.libraryapi.model.Autor;
import io.github.beletatti.libraryapi.model.GeneroLivro;
import io.github.beletatti.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-8487");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro livro2");
        livro.setDataPublicacao(LocalDate.of(1580, 6, 6));

        Autor autor = autorRepository
                .findById(UUID.fromString("a42b0a15-930f-4a87-ae93-77a50731ad58"))
                .orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }


}