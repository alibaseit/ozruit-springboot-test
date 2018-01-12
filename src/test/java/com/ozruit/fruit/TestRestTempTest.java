package com.ozruit.fruit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TestRestTempTest {
    @Autowired
    private TestRestTemplate rest;

    @Test
    public void testingTestRestTemplate() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = rest.exchange("/home", HttpMethod.GET, entity, String.class);
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .as("Rwsponse Content Test")
                .isEqualToIgnoringCase("Welcome to ozru it");
    }

    @Test
    public void testRestTemplate2() {
        String response = rest.getForObject("/home", String.class);
        assertThat(response).isEqualToIgnoringCase("Welcome to ozru it");
        assertThat("Deneme").startsWith("terer");
    }
}
