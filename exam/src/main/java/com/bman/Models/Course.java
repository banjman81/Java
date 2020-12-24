package com.bman.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="courses")
public class Course {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 
 @NotEmpty(message="Course name is required")
 private String name;
 
 @NotEmpty(message="Instructor name is required")
 private String instructor;
 
 @Min(1)
 private Integer capacity;
 
 @Column(updatable=false)
 private Date createdAt;
 private Date updatedAt;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name="user_id")
 private User user;
 
 @ManyToMany(fetch = FetchType.LAZY)
 @JoinTable(
     name = "enrollment", 
     joinColumns = @JoinColumn(name = "course_id"), 
     inverseJoinColumns = @JoinColumn(name = "user_id")
 )
 private List<User> users;
 
 public Course() {
	 
 }
 

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getInstructor() {
	return instructor;
}

public void setInstructor(String instructor) {
	this.instructor = instructor;
}

public Integer getCapacity() {
	return capacity;
}

public void setCapacity(Integer capacity) {
	this.capacity = capacity;
}

public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}

public Date getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public List<User> getUsers() {
	return users;
}

public void setUsers(List<User> users) {
	this.users = users;
}
 

@PrePersist
 protected void onCreate(){
     this.createdAt = new Date();
 }
 @PreUpdate
 protected void onUpdate(){
     this.updatedAt = new Date();
 }

}
