package com.example.restservice.Controller;

import com.example.restservice.Entitiy.Person;
import com.example.restservice.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    PersonService personService;


    @GetMapping("persons")
    public ResponseEntity<List<Person>> getPerson() {
        return new ResponseEntity<>(personService.getAllPerson(), HttpStatus.OK);

    }

    @GetMapping("persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable String id) {
        try {
            Optional<Person> person = personService.getOnePersonById(id);
            return new ResponseEntity(person, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value = "persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        try {
            return new ResponseEntity<>(personService.addOnePerson(person), HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(value = "persons/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@PathVariable String id, @RequestBody Person person) {
        try {
            return new ResponseEntity<>(personService.updateOnePerson(id, person), HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(value = "persons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePerson(@PathVariable String id) {
        try {
            personService.deleteOnePerson(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}
