package io.github.beletatti.libraryapi.controller.mappers;

import io.github.beletatti.libraryapi.controller.dto.UsuarioDTO;
import io.github.beletatti.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
