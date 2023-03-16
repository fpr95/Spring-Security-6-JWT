package com.digiteo.SpringBootJwtApi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/demo")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> helloUser(){
        return ResponseEntity.ok("Hola Usuario");
    }
}
