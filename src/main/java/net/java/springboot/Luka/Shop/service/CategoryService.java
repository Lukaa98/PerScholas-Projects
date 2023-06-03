package net.java.springboot.Luka.Shop.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.java.springboot.Luka.Shop.model.Category;
import net.java.springboot.Luka.Shop.repository.CategoryRepository;
//Business service
@Service
public class CategoryService 
{
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getAllCategory()

	{
		return categoryRepository.findAll();
	}
	
	
	public void addCategory(Category category)
	{
		categoryRepository.save(category);
		
	}

	
	
	public void removeCategoryById(int id) 
	{
		categoryRepository.deleteById(id);
	}
	
	
	
	public Optional<Category> getCategoryById(int id) 
	{
		return categoryRepository.findById(id);
	}
	
	
	
	
	
	
}
