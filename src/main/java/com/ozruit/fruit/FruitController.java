package com.ozruit.fruit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class FruitController {
    private final FruitService fruitService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping("/fruits/{name}")
    public Fruit findByName(@PathVariable("name") String name) {
        log.info("findByName method called");
        return fruitService.findByName(name);
    }

    @GetMapping("/fruits")
    public Fruit fruits() {
        log.info("fruits method called");
        Fruit fruit = new Fruit("strawberry","blue");
        fruitService.save(fruit);
        return fruit;
    }

    @PostMapping(value = "/fruits")
    public ResponseEntity<?> save(@RequestBody Fruit fruit) {
        fruitService.save(fruit);
        System.out.println(String.format("%s is saved and id is %d", fruit.getName(), fruit.getId()));
        return new ResponseEntity<>(fruit, HttpStatus.CREATED);
    }

}
