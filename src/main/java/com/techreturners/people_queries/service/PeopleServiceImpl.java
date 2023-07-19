package com.techreturners.people_queries.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.techreturners.people_queries.model.Person;
import com.techreturners.people_queries.repository.PersonRepository;
import com.techreturners.people_queries.vo.PersonVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void saveAll(List<Person> persons) {
        personRepository.saveAll(persons);
    }

    @Override
    public List<Person> loadCSVData(MultipartFile file) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        ObjectReader oReader = csvMapper.reader(PersonVO.class).with(schema);
        List<Person> persons = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            MappingIterator<PersonVO> mi = oReader.readValues(reader);
            while (mi.hasNext()) {
                PersonVO current = mi.next();
                persons.add(current.toDao());
                System.out.println(current);
            }
        }
        return persons;
    }

    @Override
    public List<Person> getPeopleByCompany(String keyword) {
        return personRepository.findByCompanyNameContaining(keyword);
    }

    @Override
    public List<Person> getPeopleByCounty(String county) {
        return personRepository.findByCountyIgnoreCase(county);
    }

    @Override
    public List<Person> getPeopleByHouseNumber(@PathVariable int digits) {
        return personRepository.findAll().stream()
                .filter(person -> {
                    String houseNumber = person.getAddress().replaceAll("\\D+", "");
                    return houseNumber.length() == digits;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getPeopleByLongURL(@PathVariable int length) {
        return personRepository.findAll().stream()
                .filter(person -> person.getWeb().length() > length)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getPeopleByPostcodeArea() {
        return personRepository.findAll().stream()
                .filter(person -> {
                    String postcode = person.getPostal();
                    String postcodeArea = postcode.split(" ")[0];
                    return postcodeArea.matches("[A-Z]*\\d");
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getPeopleByPhoneNumberComparison() {
        return personRepository.findAll().stream()
                .filter(person -> {
                    String phone1 = person.getPhone1().replaceAll("-", "");
                    String phone2 = person.getPhone2().replaceAll("-", "");
                    return isNumeric(phone1) && isNumeric(phone2) && Long.parseLong(phone1) > Long.parseLong(phone2);
                })
                .collect(Collectors.toList());
    }

    private boolean isNumeric(String str) {
        return str != null && str.matches("-?\\d+");
    }

}
