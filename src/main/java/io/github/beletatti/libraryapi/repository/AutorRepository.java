package io.github.beletatti.libraryapi.repository;

import io.github.beletatti.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

    List<Autor> findByName(String name);
    List<Autor> findByNacionalidade(String nacionalidade);
    List<Autor> findByNameAndNacionalidade(String name, String nacionalidade);

    Optional<Autor> findByNameAndDataNascimentoAndNacionalidade(
            String name, LocalDate dataNascimento, String nacionalidade);
}
