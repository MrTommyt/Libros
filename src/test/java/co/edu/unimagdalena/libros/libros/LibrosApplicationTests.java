package co.edu.unimagdalena.libros.libros;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class LibrosApplicationTests {

    @Test
    void contextLoads() {
    }

}
