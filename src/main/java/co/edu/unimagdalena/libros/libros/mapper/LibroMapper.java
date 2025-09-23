package co.edu.unimagdalena.libros.libros.mapper;

import ch.qos.logback.core.model.ComponentModel;
import co.edu.unimagdalena.libros.libros.Dto.LibroDto;
import co.edu.unimagdalena.libros.libros.entity.Libro;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LibroMapper {
    LibroDto toDto(Libro libro);
    Libro toEntity(LibroDto libroDto);
}
