package net.java.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import net.java.springboot.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{

}
