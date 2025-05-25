package io.github.beletatti.libraryapi.repository;

import io.github.beletatti.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

    List<Autor> findByName(String name);
    List<Autor> findByNacionalidade(String nacionalidade);
    List<Autor> findByNameAndNacionalidade(String name, String nacionalidade);
}
