package com.lara.fileuploadjpa.PersonRepository;

import org.springframework.data.repository.CrudRepository;

import com.lara.fileuploadjpa.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
