package org.example.test_example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTests extends PostgresContainer {

    @Test
    @DisplayName(value = "Контекст успешно инициализируется")
    void contextLoads() {

    }
}
