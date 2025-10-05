package co.edu.unimagdalena.libros.libros.entity;


import co.edu.unimagdalena.libros.libros.enums.ExchangeRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor

public class ExchangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "book_offered_id")
    private Book bookOffered;

    @ManyToOne
    @JoinColumn(name = "book_requested_id")
    private Book bookRequested;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private Client fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private Client toUser;

    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    private ExchangeRequestStatus status;

}
