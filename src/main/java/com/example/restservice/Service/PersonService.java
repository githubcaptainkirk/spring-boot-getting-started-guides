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

    public List<Person> getAllPerson() {
        return persons;
    }

    public Person getOnePersonById(int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        throw new RuntimeException("Person nicht gefunden!");
    }

    public Person addOnePerson(Person person) {
        if (persons.contains(person)) {
            throw new RuntimeException("Person schon vorhanden");
        } else {
            persons.add(person);
            return person;
        }
    }

    public Person updateOnePerson(int id, Person person) {
        person.setId(id);
        for (Person updatePerson : persons) {
            if (updatePerson.getId() == id) {
                int personIndex = persons.indexOf(updatePerson);
                persons.set(personIndex, person);
                return person;
            }
        }
        throw new RuntimeException("Person nicht gefunden!");
    }

    public String deleteOnePerson(int id) {
        for (Person deletePerson : persons) {
            if (deletePerson.getId() == id) {
                persons.remove(deletePerson);
                return ("Person wurde erfolgreich gelöscht.");
            }
        }
        throw new RuntimeException("Person wurde nicht gefunden!");
    }

}
