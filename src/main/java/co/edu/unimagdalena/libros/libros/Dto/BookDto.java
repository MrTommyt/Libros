package co.edu.unimagdalena.libros.libros.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BookDto {
    private UUID id;
    private String titulo;
    private String autor;
    private String editorial;
    private String isbn;

    public BookDto(UUID id, String titulo, String autor, String editorial, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
    }

    public BookDto() {
    }

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
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
