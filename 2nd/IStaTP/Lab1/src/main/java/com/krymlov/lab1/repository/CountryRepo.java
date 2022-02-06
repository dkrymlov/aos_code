package com.krymlov.lab1.repository;

import com.krymlov.lab1.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends CrudRepository<CountryEntity, Long> {
}
