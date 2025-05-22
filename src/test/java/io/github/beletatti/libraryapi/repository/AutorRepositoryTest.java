package io.github.beletatti.libraryapi.repository;

import io.github.beletatti.libraryapi.model.Autor;
import io.github.beletatti.libraryapi.model.GeneroLivro;
import io.github.beletatti.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {

        Autor autor = new Autor();
        autor.setName("Neuer");
        autor.setNacionalidade("Alemão");
        autor.setDataNascimento(LocalDate.of(1992, 4, 5));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);

    }

    @Test
    public void atualizarTest(){
       var id = UUID.fromString("4139c512-cd9a-437c-bc3d-04eb2d52d0ce");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 2, 7));

            repository.save(autorEncontrado);

        }
        System.out.println(possivelAutor.get());
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("16600220-3364-4271-b7ba-0d9f17e6c853");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("4139c512-cd9a-437c-bc3d-04eb2d52d0ce");
        var maria = repository.findById(id).get();
        repository.deleteById(id);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setName("Guia");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1943, 6, 4));

        Livro livro = new Livro();
        livro.setIsbn("96665-4332");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("YEAH");
        livro.setDataPublicacao(LocalDate.of(1954, 4, 6));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("96665-4332");
        livro2.setPreco(BigDecimal.valueOf(300.50));
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setTitulo("doom");
        livro2.setDataPublicacao(LocalDate.of(2025, 4, 7));
        livro2.setAutor(autor);

        autor.setLivro(new ArrayList<>());
        autor.getLivro().add(livro);
        autor.getLivro().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivro());
    }
}
