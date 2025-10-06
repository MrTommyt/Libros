package co.edu.unimagdalena.libros.libros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String state;

    @Column
    private String stateRequest;

    @ManyToOne
    @JoinColumn(name = "book_definition_id")
    private BookDefinition bookDefinition;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;
}
