package co.edu.unimagdalena.libros.libros.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String name;
    @NotBlank(message = "La direccion es obligatoria")
    private String address;
    @Email(message = "Debe ser un email válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;
    private List<BookDefinitionDto> bookDefinitions;

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
