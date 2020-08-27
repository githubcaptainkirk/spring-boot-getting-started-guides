package com.example.restservice.Service;

import com.example.restservice.Entitiy.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private List<Person> persons;

    PersonService() {
        persons = new ArrayList<>();
        persons.add(new Person("Kirk", "Murat", 1));
        persons.add(new Person("Meier", "Max", 2));
    }

    public ResponseEntity<List<Person>> getAllPerson() {
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    public ResponseEntity<Person> getOnePersonById(int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return new ResponseEntity<>(person, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Person> addOnePerson(Person person) {
        if (persons.contains(person)) {
            return new ResponseEntity<>(person, HttpStatus.CONFLICT);
        } else {
            persons.add(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        }

    }

    public ResponseEntity<Person> updateOnePerson(int id, Person person) {
        person.setId(id);
        for (Person updatePerson : persons) {
            if (updatePerson.getId() == id) {
                int personIndex = persons.indexOf(updatePerson);
                persons.set(personIndex, person);
                return new ResponseEntity<>(person, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteOnePerson(int id) {
        for (Person deletePerson : persons) {
            if (deletePerson.getId() == id) {
                persons.remove(deletePerson);
                return new ResponseEntity<>("Person gelöscht", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Person nicht vorhanden", HttpStatus.NOT_FOUND);
    }

}
