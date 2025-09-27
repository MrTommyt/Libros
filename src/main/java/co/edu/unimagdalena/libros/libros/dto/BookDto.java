package co.edu.unimagdalena.libros.libros.dto;

import co.edu.unimagdalena.libros.libros.entity.BookDefinition;
import co.edu.unimagdalena.libros.libros.entity.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private UUID id;
    private String state;
    private UUID bookDefinitionID;
    private UUID clientId;
    private BookDefinitionDto bookDefinition;


}
