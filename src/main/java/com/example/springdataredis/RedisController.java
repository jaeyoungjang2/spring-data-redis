package com.example.springdataredis;

import com.example.springdataredis.hashMappers.HashMapping;
import com.example.springdataredis.hashMappers.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final HashMapping hashMapping;

    @GetMapping("/hashmappers/_write")
    public void write() {
        hashMapping.writeHash("1",new Person());
    }

    @GetMapping("/hashmappers/_load")
    public Person load() {
        return hashMapping.loadHash("1");
    }
}
