package io.github.beletatti.libraryapi.service;

import io.github.beletatti.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.beletatti.libraryapi.model.Autor;
import io.github.beletatti.libraryapi.repository.AutorRepository;
import io.github.beletatti.libraryapi.repository.LivroRepository;
import io.github.beletatti.libraryapi.validator.AutorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;
    private final AutorValidator validator;
    private final LivroRepository livroRepository;

    public AutorService(AutorRepository repository, AutorValidator validator, LivroRepository livroRepository) {
        this.repository = repository;
        this.validator = validator;
        this.livroRepository = livroRepository;
    }

    public Autor salvar(Autor autor){
        validator.validar(autor);
        return repository.save(autor);

    }

    public void atualizar(Autor autor){
        if(autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor já esteja salvo na base.");
        }
        validator.validar(autor);
        repository.save(autor);

    }

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Autor autor){
        if(possuiLivro(autor)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir um Autor possui livros cadastrados.");
        }
        repository.delete(autor);
    }

    public List<Autor> pesquisa(String name, String nacionalidade){
        if(name != null && nacionalidade != null){
            return repository.findByNameAndNacionalidade(name, nacionalidade);
        }

        if(name != null){
            return repository.findByName(name);
        }

        if(nacionalidade != null){
            return repository.findByNacionalidade(nacionalidade);
        }

        return repository.findAll();
    }

    public boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
