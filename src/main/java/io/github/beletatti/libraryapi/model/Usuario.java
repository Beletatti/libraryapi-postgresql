package io.github.beletatti.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String login;

    @Column
    private String senha;

    @Type(ListArrayType.class)
    @Column(name = "role", columnDefinition = "varchar[]")
    private List<String> roles;
}
