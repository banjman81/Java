package com.bman.Services;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.bman.Models.Enroll;
import com.bman.Repositories.EnrollRepository;

@Service
public class EnrollService {
	private final EnrollRepository eRepo;
	
	public EnrollService(EnrollRepository eRepo) {
		this.eRepo = eRepo;
	}
	
	public Enroll addEnroll(@Valid Enroll enroll) {
		return eRepo.save(enroll);
	}

}
