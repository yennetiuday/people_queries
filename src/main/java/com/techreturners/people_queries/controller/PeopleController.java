package com.techreturners.people_queries.controller;

import com.techreturners.people_queries.model.Person;
import com.techreturners.people_queries.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty() || !file.getOriginalFilename().endsWith(".csv")) {
                return ResponseEntity.badRequest().body("Invalid file. Please upload a CSV file.");
            }

            List<Person> persons = peopleService.loadCSVData(file);

            peopleService.saveAll(persons);

            return ResponseEntity.ok("CSV file uploaded and data loaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the CSV file: " + e.getMessage());
        }
    }

    @GetMapping("/company/{keyword}")
    public ResponseEntity<List<Person>> getPeopleByCompany(@PathVariable String keyword) {
        return new ResponseEntity<>(peopleService.getPeopleByCompany(keyword), HttpStatus.OK);
    }

    @GetMapping("/county/{county}")
    public ResponseEntity<List<Person>> getPeopleByCounty(@PathVariable String county) {
        return new ResponseEntity<>(peopleService.getPeopleByCounty(county), HttpStatus.OK);
    }

    @GetMapping("/houseNumber/{digits}")
    public ResponseEntity<List<Person>> getPeopleByHouseNumber(@PathVariable int digits) {
        return new ResponseEntity<>(peopleService.getPeopleByHouseNumber(digits), HttpStatus.OK);
    }

    @GetMapping("/longurl/{length}")
    public ResponseEntity<List<Person>> getPeopleByLongURL(@PathVariable int length) {
        return new ResponseEntity<>(peopleService.getPeopleByLongURL(length), HttpStatus.OK);
    }

    @GetMapping("/postcodearea")
    public ResponseEntity<List<Person>> getPeopleByPostcodeArea() {
        return new ResponseEntity<>(peopleService.getPeopleByPostcodeArea(), HttpStatus.OK);
    }

    @GetMapping("/phonecomparison")
    public ResponseEntity<List<Person>> getPeopleByPhoneNumberComparison() {
        return new ResponseEntity<>(peopleService.getPeopleByPhoneNumberComparison(), HttpStatus.OK);
    }

}
