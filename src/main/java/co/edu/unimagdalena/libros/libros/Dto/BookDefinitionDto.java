package co.edu.unimagdalena.libros.libros.Dto;

import jakarta.persistence.Table;
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



//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public String getTitulo() {
//        return titulo;
//    }
//
//    public void setTitulo(String titulo) {
//        this.titulo = titulo;
//    }
//
//    public String getAutor() {
//        return autor;
//    }
//
//    public void setAutor(String autor) {
//        this.autor = autor;
//    }
//
//    public String getEditorial() {
//        return editorial;
//    }
//
//    public void setEditorial(String editorial) {
//        this.editorial = editorial;
//    }
//
//    public String getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(String isbn) {
//        this.isbn = isbn;
//    }

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
