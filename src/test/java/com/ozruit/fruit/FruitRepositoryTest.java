package com.ozruit.fruit;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FruitRepositoryTest {
    @Autowired
    private FruitRepository fruitRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testSaveFruit() {
        Fruit fruit = new Fruit("plum", "blue");
        Fruit savedFruit = testEntityManager.persistFlushFind(fruit);
        Fruit fidFruit = fruitRepository.findByName("plum");

        Assertions.assertThat(savedFruit.getName()).isEqualToIgnoringCase(fidFruit.getName());

    }
}