package com.ozruit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    ResponseEntity<?> home() {
        return new ResponseEntity<>("Welcome To Ozru IT", HttpStatus.OK);
    }
}
