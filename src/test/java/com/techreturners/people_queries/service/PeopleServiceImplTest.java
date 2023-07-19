package com.techreturners.people_queries.service;

import com.techreturners.people_queries.model.Person;
import com.techreturners.people_queries.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class PeopleServiceImplTest {
    @Mock
    PersonRepository personRepository;
    @InjectMocks
    PeopleServiceImpl peopleServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAll() {
        List<Person> persons = new ArrayList<>();
        peopleServiceImpl.saveAll(persons);
        verify(personRepository, times(1)).saveAll(persons);
    }

    @Test
    void testGetPeopleByCompany() {
        when(personRepository.findByCompanyNameContaining(anyString())).thenReturn(List.of(new Person(Long.valueOf(1),
                "firstName", "lastName", "companyName", "address", "city",
                "county", "postal", "phone1", "phone2", "email", "web")));
        List<Person> result = peopleServiceImpl.getPeopleByCompany("keyword");
        Assertions.assertEquals(List.of(new Person(Long.valueOf(1), "firstName", "lastName",
                "companyName", "address", "city", "county", "postal",
                "phone1", "phone2", "email", "web")), result);
    }

    @Test
    void testGetPeopleByCounty() {
        when(personRepository.findByCountyIgnoreCase(anyString())).thenReturn(List.of(new Person(Long.valueOf(1),
                "firstName", "lastName", "companyName", "address", "city",
                "county", "postal", "phone1", "phone2", "email", "web")));

        List<Person> result = peopleServiceImpl.getPeopleByCounty("county");
        Assertions.assertEquals(List.of(new Person(Long.valueOf(1), "firstName", "lastName",
                "companyName", "address", "city", "county", "postal",
                "phone1", "phone2", "email", "web")), result);
    }

    @Test
    void testGetPeopleByHouseNumber() {
        when(personRepository.findAll()).thenReturn(List.of(new Person(Long.valueOf(1), "firstName",
                "lastName", "companyName", "123 address", "city", "county",
                "postal", "phone1", "phone2", "email", "web")));
        List<Person> result = peopleServiceImpl.getPeopleByHouseNumber(3);
        Assertions.assertEquals(List.of(new Person(Long.valueOf(1), "firstName", "lastName",
                "companyName", "123 address", "city", "county", "postal",
                "phone1", "phone2", "email", "web")), result);
    }

    @Test
    void testGetPeopleByLongURL() {
        when(personRepository.findAll()).thenReturn(List.of(new Person(Long.valueOf(1), "firstName",
                "lastName", "companyName", "address", "city", "county",
                "postal", "phone1", "phone2", "email", "www.abcdefghijklmnopqrstuvwxyz.com")));
        List<Person> result = peopleServiceImpl.getPeopleByLongURL(0);
        Assertions.assertEquals(List.of(new Person(Long.valueOf(1), "firstName", "lastName",
                "companyName", "address", "city", "county", "postal",
                "phone1", "phone2", "email", "www.abcdefghijklmnopqrstuvwxyz.com")), result);
    }

    @Test
    void testGetPeopleByPostcodeArea() {
        when(personRepository.findAll()).thenReturn(List.of(new Person(Long.valueOf(1), "firstName",
                "lastName", "companyName", "address", "city", "county",
                "TW5 9BP", "phone1", "phone2", "email", "web")));
        List<Person> result = peopleServiceImpl.getPeopleByPostcodeArea();
        Assertions.assertEquals(List.of(new Person(Long.valueOf(1), "firstName", "lastName",
                "companyName", "address", "city", "county", "TW5 9BP",
                "phone1", "phone2", "email", "web")), result);
    }

    @Test
    void testGetPeopleByPhoneNumberComparison() {
        when(personRepository.findAll()).thenReturn(List.of(new Person(Long.valueOf(1), "firstName",
                "lastName", "companyName", "address", "city", "county",
                "postal", "01234-56789", "01134-56789", "email", "web")));
        List<Person> result = peopleServiceImpl.getPeopleByPhoneNumberComparison();
        Assertions.assertEquals(List.of(new Person(Long.valueOf(1), "firstName", "lastName",
                "companyName", "address", "city", "county", "postal",
                "01234-56789", "01134-56789", "email", "web")), result);
    }
}