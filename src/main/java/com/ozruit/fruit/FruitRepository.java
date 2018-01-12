package com.ozruit.fruit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends CrudRepository<Fruit, Integer> {
    public Fruit findByName(String name);
}
