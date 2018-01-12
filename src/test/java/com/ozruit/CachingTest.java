package com.ozruit;

import com.ozruit.fruit.Fruit;
import com.ozruit.fruit.FruitRepository;
import com.ozruit.fruit.FruitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class CachingTest {

    @Autowired
    private FruitService fruitService;

    @MockBean
    private FruitRepository fruitRepository;

    @Test
    public void caching() {
        given(fruitRepository.findByName(anyString())).willReturn(new Fruit("apple", "green"));

        fruitService.findByName("apple");
        fruitService.findByName("apple");

        verify(fruitRepository, times(2)).findByName("apple");
    }
}
