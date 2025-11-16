package com.scex.testapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class TestController {


    @GetMapping(path = "/customer/{id}")
    public ResponseEntity<String> customer(@PathVariable("id") String id) {
        return ResponseEntity.ok("Good morning");
    }
}
