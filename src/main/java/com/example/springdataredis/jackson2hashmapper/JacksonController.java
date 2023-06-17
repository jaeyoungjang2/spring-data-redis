package com.example.springdataredis;

import com.example.springdataredis.hashMappers.StudyHashMapping;
import com.example.springdataredis.hashMappers.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final StudyHashMapping studyHashMapping;

    @GetMapping("/hashmappers/_write")
    public void hashmappersWrite() {
        studyHashMapping.writeHash("1",new Person());
    }

    @GetMapping("/hashmappers/_load")
    public Person hashmappersLoad() {
        return studyHashMapping.loadHash("1");
    }@GetMapping("/hashmappers/_write")

    public void hashmappersWrite() {
        studyHashMapping.writeHash("1",new Person());
    }

    @GetMapping("/hashmappers/_load")
    public Person hashmappersLoad() {
        return studyHashMapping.loadHash("1");
    }
}
