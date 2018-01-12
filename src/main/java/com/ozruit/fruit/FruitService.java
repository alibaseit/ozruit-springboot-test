package com.ozruit.fruit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FruitService {
    private final FruitRepository fruitRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Cacheable("fruits")
    public Fruit findByName(String name) {
        log.info("FruitService findByName called name is " + name);
        Fruit fruit = this.fruitRepository.findByName(name);
        if (fruit == null)
            throw new FruitNotFoundException();
        return fruit;
    }

    public Fruit save(Fruit fruit) {
        return this.fruitRepository.save(fruit);
    }
}
