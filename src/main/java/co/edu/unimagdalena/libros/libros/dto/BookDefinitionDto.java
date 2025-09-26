package co.edu.unimagdalena.libros.libros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDefinitionDto {

    @NotNull
    private UUID id;

    @NotBlank(message = "El titulo no puede estar vacio")
    private String title;
    private String author;
    private String editorial;
    private String isbn;

    @Override
    public String toString() {
        return "LibroDto{" +
                "id=" + id +
                ", titulo='" + title + '\'' +
                ", autor='" + author + '\'' +
                ", editorial='" + editorial + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
