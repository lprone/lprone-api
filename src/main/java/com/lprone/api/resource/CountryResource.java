package com.lprone.api.resource;

import com.lprone.api.domain.Country;
import com.lprone.api.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CountryResource {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/country")
    public List<Country> getAllCountries() {
        System.out.println("getAllCountries");
        return countryRepository.findAll();
    }

    @GetMapping("/country/{name}")
    public ResponseEntity<Country> getCountryByName(@PathVariable(value = "name") String name) throws Exception {
        System.out.println("getPersonById " + name);

        Country person = countryRepository
                .findById(name)
                .orElseThrow(() -> new Exception("Country not found on :: " + name));

        return ResponseEntity
                .ok()
                .body(person);
    }

    @PostMapping("/country")
    public Country createCountry(@Valid @ModelAttribute Country country) {
        System.out.println("createCountry " + country);

        return countryRepository.save(country);
    }

    @PutMapping("/country/{name}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "name") Long userId, @Valid @ModelAttribute Country country) {
        System.out.println("updateCountry "+ userId + " - " + country);

        final Country updatedCountry = countryRepository.save(country);

        return ResponseEntity.ok(updatedCountry);
    }

    @DeleteMapping("/country/{name}")
    public Map<String, Boolean> deleteCountry(@PathVariable(value = "name") String name) throws Exception {
        System.out.println("deleteCountry " + name);
        Country country = countryRepository
                .findById(name)
                .orElseThrow(() -> new Exception("Country not found on :: " + name));

        countryRepository.delete(country);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
