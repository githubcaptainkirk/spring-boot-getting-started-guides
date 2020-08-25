package com.example.restservice;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.ValueExp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    private List<Person> persons;

    PersonController() {
        persons = new ArrayList<>();
        persons.add(new Person("Kirk", "Murat", 1));
        persons.add(new Person("Meier", "Max", 2));
    }


    @GetMapping("persons")
    public ResponseEntity<List<Person>> getPerson() {
        ResponseEntity responseEntity = new ResponseEntity<>(persons, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                ResponseEntity responseEntity = new ResponseEntity<>(person, HttpStatus.OK);
                return responseEntity;
            }
        }
        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @PostMapping(value = "persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {

        if (persons.contains(person)) {
            ResponseEntity responseEntity = new ResponseEntity<>(person, HttpStatus.CONFLICT);
            return responseEntity;
        } else {
            persons.add(person);
            ResponseEntity responseEntity = new ResponseEntity<>(person, HttpStatus.OK);
            return responseEntity;
        }

    }

    @PutMapping(value = "persons/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        person.setId(id);
        for (Person updatePerson : persons) {
            if (updatePerson.getId() == id) {
                int personIndex = persons.indexOf(updatePerson);
                persons.set(personIndex, person);
                ResponseEntity responseEntity = new ResponseEntity<>(person, HttpStatus.OK);
                return responseEntity;

            }
        }
        ResponseEntity responseEntity = new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @DeleteMapping(value = "persons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePerson(@PathVariable int id) {
        for (Person deletePerson : persons) {
            if (deletePerson.getId() == id) {
                persons.remove(deletePerson);
                ResponseEntity responseEntity = new ResponseEntity<>("Person gelöscht", HttpStatus.OK);
                return responseEntity;
            }
        }
        ResponseEntity responseEntity = new ResponseEntity<>("Person nicht vorhanden", HttpStatus.NOT_FOUND);
        return responseEntity;

    }


}
