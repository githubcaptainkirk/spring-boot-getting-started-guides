package com.example.restservice.Entitiy;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Person {

    private String vorname;
    private String name;
    @Id
    private String id;

}
