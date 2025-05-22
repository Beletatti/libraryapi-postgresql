package io.github.beletatti.libraryapi.repository;

import io.github.beletatti.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

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
}
