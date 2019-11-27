package com.ellien.spring5.controllers;

import com.ellien.spring5.entities.Person;
import com.ellien.spring5.services.PersonService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author ellien
 * @date 2018/04/16 15:27
 */
@RestController
public class PersonController {


    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    Mono<Person> create(@PathVariable("id") final Long id) {
        return personService.getById(id);
    }

}
