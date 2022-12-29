package ru.netology.springbootdemo;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Container
    private static final GenericContainer<?> MYAPP1 = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> MYAPP2 = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    /*@BeforeAll
    public static void setUp() {
        MYAPP1.start();
        MYAPP2.start();
    }*/

    @Test
    void contextLoads() {
        Integer firstPort = MYAPP1.getMappedPort(8080);
        Integer secondPort = MYAPP2.getMappedPort(8081);

        ResponseEntity<String> forEntity1 = restTemplate.getForEntity("http://localhost:" + firstPort, String.class);
        System.out.println("First devapp" + forEntity1.getBody());
        Assertions.assertEquals(MYAPP1.getExposedPorts(), List.of(8080));
        Assertions.assertEquals(forEntity1.getBody(), "Current profile is dev");

        ResponseEntity<String> forEntity2 = restTemplate.getForEntity("http://localhost:" + secondPort, String.class);
        System.out.println("Second prodapp" + forEntity2.getBody());
        Assertions.assertEquals(MYAPP2.getExposedPorts(), List.of(8081));
        Assertions.assertEquals(forEntity2.getBody(), "Current profile is production");

    }
}
