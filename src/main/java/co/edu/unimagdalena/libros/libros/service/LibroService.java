package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.Dto.LibroDto;
import co.edu.unimagdalena.libros.libros.entity.Libro;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LibroService {
    Optional<LibroDto> findAll();
    List<LibroDto> save(LibroDto libroDto);
    List<LibroDto> update(UUID id, LibroDto newLibroDto);
    void delete(LibroDto libroDto);
}
