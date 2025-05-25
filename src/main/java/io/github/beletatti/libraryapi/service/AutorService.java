package io.github.beletatti.libraryapi.service;

import io.github.beletatti.libraryapi.model.Autor;
import io.github.beletatti.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);

    }

    public void atualizar(Autor autor){
        if(autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor já esteja salvo na base.");
        }
        repository.save(autor);

    }

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Autor autor){
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
}
