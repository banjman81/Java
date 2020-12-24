package com.bman.Services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.bman.Models.Course;
import com.bman.Repositories.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository cRepo;
	
	public CourseService(CourseRepository cRepo) {
		this.cRepo = cRepo;
	}
	
	public List<Course> allCourses(){
		return cRepo.findAll();
	}

	public Course createCourse(@Valid Course course) {
		return cRepo.save(course);
	}

	public Course findCourse(Long id) {
		Optional<Course> c =  cRepo.findById(id);
		if(c.isPresent()) {
			return c.get();
		}
		return null;
	}

	public Course updateCourse(String name, String instructor, Integer cap, Long id) {
		Course co = findCourse(id);
		co.setName(name);
		co.setInstructor(instructor);
		co.setCapacity(cap);
		return cRepo.save(co);
	}

	public void deleteCourse(Long id) {
		cRepo.deleteById(id);
		return;
		
	}

	public Course editCourse(@Valid Course course) {
		return cRepo.save(course);
		
	}
}
