package co.edu.unimagdalena.libros.libros;

import org.springframework.boot.SpringApplication;

public class TestLibrosApplication {

	public static void main(String[] args) {
		SpringApplication.from(LibrosApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
