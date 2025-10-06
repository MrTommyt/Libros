package co.edu.unimagdalena.libros.libros.dto;

import co.edu.unimagdalena.libros.libros.entity.BookDefinition;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private UUID id;
    private String name;
    private String address;
    private String email;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nombre='" + name + '\'' +
                ", direccion='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
