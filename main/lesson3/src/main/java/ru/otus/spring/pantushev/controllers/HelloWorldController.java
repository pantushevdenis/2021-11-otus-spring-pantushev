/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package ru.otus.spring.pantushev.controllers;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.pantushev.domains.Greating;

@RestController
public class HelloWorldController {
    final AtomicLong counter = new AtomicLong();
    
    @GetMapping("/helloworld")
    public Greating helloworld()
    {
        return new Greating(counter.incrementAndGet(), "Hello, world!");
    }

    
    
    
}
