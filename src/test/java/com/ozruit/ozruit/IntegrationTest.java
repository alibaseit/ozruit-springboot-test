package com.ozruit.ozruit;

import com.ozruit.fruit.Fruit;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void home() {
        ResponseEntity<String> response = restTemplate.getForEntity("/home", String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(response.getBody()).isEqualToIgnoringCase("Welcome To Ozru IT");

    }

    @Test
    public void test_fruits_list() {
        ResponseEntity<Fruit> response = restTemplate.getForEntity("/fruits", Fruit.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getName()).isEqualToIgnoringCase("strawberry");
    }

    @Test
    public void insert_fruit_test() {
        Fruit fruit = new Fruit("Pineapple", "Brown");
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(fruit, httpHeaders);
        ResponseEntity<Fruit> response = restTemplate.exchange("/fruits", HttpMethod.POST, httpEntity, Fruit.class);
        Assertions.assertThat(response.getBody().getName()).isEqualToIgnoringCase("Pineapple");

        ResponseEntity<Fruit> exchange = restTemplate.exchange("/fruits/Pineapple", HttpMethod.GET, new HttpEntity<>(null, httpHeaders), Fruit.class);
        Assertions.assertThat(exchange.getBody().getColor()).isEqualToIgnoringCase("Brown");

    }
}