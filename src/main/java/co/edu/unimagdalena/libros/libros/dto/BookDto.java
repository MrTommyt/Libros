package co.edu.unimagdalena.libros.libros.dto;

import co.edu.unimagdalena.libros.libros.entity.BookDefinition;
import co.edu.unimagdalena.libros.libros.entity.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String state;
    private BookDefinition bookDefinition;
    private Client client;
}
