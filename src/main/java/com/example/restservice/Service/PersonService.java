package com.example.restservice.Service;

import com.example.restservice.Entitiy.Person;
import com.example.restservice.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private List<Person> persons;

    @Autowired
    PersonRepository personRepository;

    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    public Optional<Person> getOnePersonById(String id) {
        if (personRepository.findById(id).isPresent()) {
            return personRepository.findById(id);
        } else {
            throw new RuntimeException("Person nicht gefunden!");
        }
    }

    public Person addOnePerson(Person person) {
        return personRepository.save(person);
    }

    public Person updateOnePerson(String id, Person person) {
        person.setId(id);
        if (personRepository.findById(id).isPresent()) {
            return personRepository.save(person);
        }
        throw new RuntimeException("Person nicht gefunden!");
    }

    public void deleteOnePerson(String id) {
        if (personRepository.findById(id).isPresent()) {
           personRepository.deleteById(id);
        } else {
            throw new RuntimeException("Person wurde nicht gefunden!");
        }
    }

}
