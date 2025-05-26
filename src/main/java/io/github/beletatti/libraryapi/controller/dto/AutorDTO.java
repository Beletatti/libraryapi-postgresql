package io.github.beletatti.libraryapi.controller.dto;

import io.github.beletatti.libraryapi.model.Autor;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Campo obrigatório")
        @Size(min= 2, max = 100, message = "campo fora do tamanho padrao")
        String nome,
        @NotNull(message = "Campo obrigatório")
        @Past(message = "Não pode ser uma data futura")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo obrigatório")
        @Size(max= 50, min = 2, message = "campo fora do tamanho padrao")
        String nacionalidade) {

    public Autor mapearParaAutor(){

        Autor autor = new Autor();
        autor.setName(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);

        return autor;

    }

}
