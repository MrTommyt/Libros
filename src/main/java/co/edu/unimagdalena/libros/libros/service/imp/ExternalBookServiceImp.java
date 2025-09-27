package co.edu.unimagdalena.libros.libros.service.imp;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.service.ExternalBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class ExternalBookServiceImp implements ExternalBookService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<BookDefinitionDto> fetchBooksFromGoogle(String query) {
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + query;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        List<BookDefinitionDto> results = new ArrayList<>();

        if (response == null || !response.containsKey("items")) {
            return results;
        }

        List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");
        for (Map<String, Object> item : items) {
            Map<String, Object> volumeInfo = (Map<String, Object>) item.get("volumeInfo");

            BookDefinitionDto dto = new BookDefinitionDto();
            dto.setTitle((String) volumeInfo.getOrDefault("title", "Sin título"));

            // Autor
            List<String> authors = (List<String>) volumeInfo.get("authors");
            if (authors != null && !authors.isEmpty()) {
                dto.setAuthor(authors.get(0));
            } else {
                dto.setAuthor("Autor desconocido");
            }

            // Editorial
            dto.setEditorial((String) volumeInfo.getOrDefault("publisher", "Editorial desconocida"));

            // ISBN
            List<Map<String, String>> identifiers = (List<Map<String, String>>) volumeInfo.get("industryIdentifiers");
            if (identifiers != null && !identifiers.isEmpty()) {
                dto.setIsbn(identifiers.get(0).get("identifier"));
            } else {
                dto.setIsbn("SIN-ISBN-" + Math.random());
            }

            results.add(dto);
        }

        return results;
    }
}
