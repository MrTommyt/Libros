package co.edu.unimagdalena.libros.libros.mapper;

import ch.qos.logback.core.model.ComponentModel;
import co.edu.unimagdalena.libros.libros.Dto.LibroDto;
import co.edu.unimagdalena.libros.libros.entity.Libro;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LibroMapper {
    LibroDto toDto(Libro libro);
    Libro toEntity(LibroDto libroDto);

    LibroMapper INSTANCE = Mappers.getMapper(LibroMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "titulo", target = "titulo"),
            @Mapping(source = "autor", target = "autor"),
            @Mapping(source = "editorial", target = "editorial"),
            @Mapping(source = "isbn", target = "isbn")
    })
    LibroDto toLibroDto(Libro libro);

    @InheritInverseConfiguration
    Libro toLibro(LibroDto libroDto);
}
