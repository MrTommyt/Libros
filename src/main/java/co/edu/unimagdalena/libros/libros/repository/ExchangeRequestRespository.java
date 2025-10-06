package co.edu.unimagdalena.libros.libros.repository;

import co.edu.unimagdalena.libros.libros.dto.ExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.entity.ExchangeRequest;
import co.edu.unimagdalena.libros.libros.enums.ExchangeRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExchangeRequestRespository extends JpaRepository<ExchangeRequest, UUID> {
    List<ExchangeRequest> findByBookOfferedId(UUID bookOfferedId);
    List<ExchangeRequest> findByToUserId(UUID toUserId);
    List<ExchangeRequest> findByFromUserId(UUID fromUserId);
    List<ExchangeRequest> findByStatus(ExchangeRequestStatus status);

    Optional<ExchangeRequest> findByid(UUID id);
}
