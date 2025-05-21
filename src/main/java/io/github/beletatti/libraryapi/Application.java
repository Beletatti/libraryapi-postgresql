package io.github.beletatti.libraryapi;

import io.github.beletatti.libraryapi.model.Autor;
import io.github.beletatti.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
		AutorRepository repository = context.getBean(AutorRepository.class);

		exemploSalvarRegistro(repository);
	}

	public static void exemploSalvarRegistro(AutorRepository autorRepository) {
		Autor autor = new Autor();
		autor.setName("Kane");
		autor.setNacionalidade("Inglesa");
		autor.setDataNascimento(LocalDate.of(1995, 1, 12));

		var autorSalvo = autorRepository.save(autor);
		System.out.println("Autor Salvo: " + autorSalvo);
	}


}