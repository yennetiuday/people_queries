package com.techreturners.people_queries.service;

import com.techreturners.people_queries.model.Person;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PeopleService {
    void saveAll(List<Person> persons);

    List<Person> loadCSVData(MultipartFile file) throws IOException;

    List<Person> getPeopleByCompany(String keyword);

    List<Person> getPeopleByCounty(String county);

    List<Person> getPeopleByHouseNumber(@PathVariable int digits);

    List<Person> getPeopleByLongURL(@PathVariable int length);

    List<Person> getPeopleByPostcodeArea();

    List<Person> getPeopleByPhoneNumberComparison();
}
