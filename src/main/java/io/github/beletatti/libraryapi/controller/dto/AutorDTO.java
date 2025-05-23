package io.github.beletatti.libraryapi.controller.dto;

import io.github.beletatti.libraryapi.model.Autor;

import java.time.LocalDate;

public record AutorDTO(
        String nome,
        LocalDate dataNascimento,
        String nacionalidade) {

    public Autor mapearParaAutor(){

        Autor autor = new Autor();
        autor.setName(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);

        return autor;

    }

}
