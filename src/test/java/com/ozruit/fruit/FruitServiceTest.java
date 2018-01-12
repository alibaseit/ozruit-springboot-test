package com.ozruit.fruit;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class FruitServiceTest {

    @Mock
    private FruitRepository fruitRepository;

    private FruitService fruitService;

    @Before
    public void setUp() {
        fruitService = new FruitService(fruitRepository);
    }

    @Test
    public void test_findCarByName() {
        given(fruitService.findByName(anyString())).willReturn(new Fruit("banana", "yellow"));
        Fruit fruit = fruitService.findByName("ddd");
        Assertions.assertThat(fruit.getName()).isEqualToIgnoringCase("banana");
    }

    @Test(expected = FruitNotFoundException.class)
    public void test_NotFoundCar() throws Exception{
        given(fruitService.findByName("fdf")).willReturn(null);
        fruitService.findByName("dfdf");
//        Assertions.assertThat();
    }
}