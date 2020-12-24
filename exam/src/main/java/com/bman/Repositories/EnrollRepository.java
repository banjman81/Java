package com.bman.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bman.Models.Enroll;

@Repository
public interface EnrollRepository extends CrudRepository<Enroll, Long>{
	List<Enroll> findAll();
}
