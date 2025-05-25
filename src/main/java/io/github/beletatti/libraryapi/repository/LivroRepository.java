package io.github.beletatti.libraryapi.repository;

import io.github.beletatti.libraryapi.model.Autor;
import io.github.beletatti.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    boolean existsByAutor(Autor autor);
}
