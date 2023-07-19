package com.techreturners.people_queries.repository;

import com.techreturners.people_queries.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByCompanyNameContaining(String keyword);

    List<Person> findByCountyIgnoreCase(String county);
}
