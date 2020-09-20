package com.example.restservice.Repository;

import com.example.restservice.Entitiy.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
