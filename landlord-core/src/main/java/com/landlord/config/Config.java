package com.landlord.config;

import com.landlord.domain.person.PersonRepository;
import com.landlord.domain.property.PropertyFactory;
import com.landlord.domain.property.PropertyRepository;
import com.landlord.infrastructure.adapters.secondary.persistence.inmemory.PersonRepositoryInMemory;
import com.landlord.infrastructure.adapters.secondary.persistence.inmemory.PropertyRepositoryInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.landlord")
public class Config {

    @Bean
    public PersonRepository personRepository() {
        return new PersonRepositoryInMemory();
    }

    @Bean
    public PropertyRepository propertyRepository() {
        return new PropertyRepositoryInMemory();
    }

    @Bean
    public PropertyFactory propertyFactory() {
        return new PropertyFactory();
    }
}
