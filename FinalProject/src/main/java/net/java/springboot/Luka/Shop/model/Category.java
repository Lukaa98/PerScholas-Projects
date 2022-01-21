package net.java.springboot.Luka.Shop.model;

import javax.persistence.Id;
import javax.persistence.Table;

//import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
//@Data
@Table(name = "Category")

public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		
	@Column(name = "caregory_id")
	private int id;

	@Column(name = "name")
	private String name;

	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




}
