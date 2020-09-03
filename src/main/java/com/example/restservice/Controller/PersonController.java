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
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(personService.getOnePersonById(id), HttpStatus.OK);
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
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        try {
            return new ResponseEntity<>(personService.updateOnePerson(id, person), HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(value = "persons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePerson(@PathVariable int id) {
        try {
            return new ResponseEntity<>(personService.deleteOnePerson(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}
