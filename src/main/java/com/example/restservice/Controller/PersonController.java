package com.example.restservice.Controller;

import com.example.restservice.Entitiy.Person;
import com.example.restservice.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return personService.getAllPerson();

    }

    @GetMapping("persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        return personService.getOnePersonById(id);
    }

    @PostMapping(value = "persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return personService.addOnePerson(person);
    }

    @PutMapping(value = "persons/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        return personService.updateOnePerson(id, person);
    }

    @DeleteMapping(value = "persons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePerson(@PathVariable int id) {
        return personService.deleteOnePerson(id);
    }


}
