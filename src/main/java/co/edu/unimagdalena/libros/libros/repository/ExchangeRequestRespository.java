package co.edu.unimagdalena.libros.libros.repository;

import co.edu.unimagdalena.libros.libros.dto.ExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.dto.ExchangeView;
import co.edu.unimagdalena.libros.libros.entity.ExchangeRequest;
import co.edu.unimagdalena.libros.libros.enums.ExchangeRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExchangeRequestRespository extends JpaRepository<ExchangeRequest, UUID> {
    List<ExchangeRequest> findByBookOfferedId(UUID bookOfferedId);
    List<ExchangeRequest> findByToUserId(UUID toUserId);
    List<ExchangeRequest> findByFromUserId(UUID fromUserId);
    List<ExchangeRequest> findByStatus(ExchangeRequestStatus status);
    List<ExchangeRequest> findByFromUserIdAndStatus(UUID fromUserId, ExchangeRequestStatus status);

    Optional<ExchangeRequest> findById(UUID id);

    @Query("""
select new co.edu.unimagdalena.libros.libros.dto.ExchangeView(
       er.id,
       bo.bookDefinition.title,
       br.bookDefinition.title,
       case when er.fromUser.id = :uid then er.toUser.name else er.fromUser.name end,
       er.localDateTime
)
from ExchangeRequest er
join er.bookOffered   bo
join er.bookRequested br
where er.status = co.edu.unimagdalena.libros.libros.enums.ExchangeRequestStatus.COMPLETED
  and (er.fromUser.id = :uid or er.toUser.id = :uid)
order by er.localDateTime desc
""")
    List<ExchangeView> findMyCompletedExchanges(UUID uid);
}
