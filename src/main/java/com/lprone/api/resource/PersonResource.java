package com.lprone.api.resource;

import com.lprone.api.repository.PersonRepository;
import com.lprone.api.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/person")
    public List<Person> getAllPerson() {
        System.out.println("getAllPerson");
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") long id) throws Exception {
        System.out.println("getPersonById " + id);

        Person person = personRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Person not found on :: " + id));

        return ResponseEntity
                .ok()
                .body(person);
    }


    @PostMapping("/person")
    public Person createPerson(@Valid @ModelAttribute Person person) {
        System.out.println("createPerson " + person);

        return personRepository.save(person);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long userId, @Valid @ModelAttribute Person person) {
        System.out.println("updatePerson "+ userId + " - " + person);

        final Person updatedPerson = personRepository.save(person);

        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/person/{id}")
    public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long id) throws Exception {
        System.out.println("deletePerson " + id);
        Person person = personRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Person not found on :: " + id));

        personRepository.delete(person);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
