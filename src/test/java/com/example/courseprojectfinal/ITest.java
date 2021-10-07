package com.example.courseprojectfinal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITest {
    private static final int port = 8080;
    private static final String transfer = "/transfer";
    private static final String confirmOperation = "/confirmOperation";

    private final static Path RESOURCE_PATH = Path.of(System.getProperty("user.dir"));

    String cardFromNumber = "6666666666666666";
    String cardFromValidTill = "12/34";
    String cardFromCVV = "666";
    String cardToNumber = "7777777777777777";
    int value = 2543;
    String currency = "RUR";
    String operationId = "999999999";
    String code = "000";


    @Autowired
    private TestRestTemplate restTemplate;
    public static GenericContainer<?> courseApp = new GenericContainer<>(new ImageFromDockerfile()
            .withFileFromPath(".", RESOURCE_PATH)).
            withExposedPorts(port);

    @BeforeAll
    public static void setUp() {
        courseApp.start();
    }

    @Test
    void testSave() {
        String jsonString = String.format("{\"cardFromNumber\": \"%s\", \"cardFromValidTill\": \"%s\", \"cardFromCVV\": \"%s\", " +
                        "\"cardToNumber\":  \"%s\", \"amount\": {\"value\": \"%d\", \"currency\": \"%s\" }}",
                cardFromNumber, cardFromValidTill, cardFromCVV, cardToNumber, value, currency);
        final String baseUrl = String.format("http://localhost:%d%s", courseApp.getMappedPort(port), transfer);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);
        assertEquals(response.getStatusCode(), (HttpStatus.OK));
        String operationId = response.getBody();

        Assertions.assertNotNull(operationId);
        Assertions.assertNotEquals(operationId, "");

    }

    @Test
    void testConfirm() {
        String jsonString = String.format("{\"operationId\": \"%s\", \"code\": \"%s\"}",
                operationId, code);
        final String baseUrl = String.format("http://localhost:%d%s", courseApp.getMappedPort(port), confirmOperation);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);

        assertEquals((HttpStatus.OK), response.getStatusCode());
        String operationId = response.getBody();

        Assertions.assertNotNull(operationId);
        Assertions.assertNotEquals(operationId, "");

    }

}
