package com.techreturners.people_queries.vo;

import com.techreturners.people_queries.model.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonVO {
    private String first_name;
    private String last_name;
    private String company_name;
    private String address;
    private String city;
    private String county;
    private String postal;
    private String phone1;
    private String phone2;
    private String email;
    private String web;

    public Person toDao() {
        return Person.builder().firstName(first_name)
                .lastName(last_name)
                .companyName(company_name)
                .address(address)
                .city(city)
                .county(county)
                .postal(postal)
                .phone1(phone1)
                .phone2(phone2)
                .email(email)
                .web(web)
                .build();
    }
}
