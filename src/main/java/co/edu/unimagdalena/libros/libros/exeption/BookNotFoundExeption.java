package co.edu.unimagdalena.libros.libros.exeption;

public class BookNotFoundExeption extends RuntimeException {
    public BookNotFoundExeption(String message) {
        super(message);
    }
}
