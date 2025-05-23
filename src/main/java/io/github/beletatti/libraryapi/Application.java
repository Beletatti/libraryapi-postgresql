package io.github.beletatti.libraryapi;

import io.github.beletatti.libraryapi.model.Autor;
import io.github.beletatti.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}


}