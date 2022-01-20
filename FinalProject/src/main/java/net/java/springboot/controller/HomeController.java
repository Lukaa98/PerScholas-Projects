package net.java.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



import java.util.Optional;

import net.java.springboot.DataTransferObject.DTO.ProductDTO;
import net.java.springboot.Global.Global;
import net.java.springboot.model.Category;
import net.java.springboot.model.Product;
import net.java.springboot.service.CategoryService;
import net.java.springboot.service.ProductService;



@Controller
public class HomeController {
	@Autowired  //to declear dependecy 
    private	CategoryService categoryService;
	@Autowired
	private ProductService productService;

	
	/*
	@GetMapping ({"/" , "/home"})
	public String home(Model model)
	{
		
		model.addAttribute("cartCount", Global.cart.size());

		return "shop";

	}
	*/
	
	
	@GetMapping ({"/" , "/home", "/shop"})
	public String shop(Model model)
	{
		model.addAttribute("cartCount", Global.cart.size());

		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		return "shop";

	}
	
	//displays each product from each category on shop page
	@GetMapping ("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id)
	{
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		return "shop";

	}
	
	//to view each product by pressing view product button on shop page
	@GetMapping ("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable int id)
	{
		model.addAttribute("product", productService.getProductById(id).get());
		model.addAttribute("cartCount", Global.cart.size());

		return "viewProduct";
	}
	
	
	
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index)
	{
		Global.cart.remove(index);
		return "redirect:/cart";
	}
	
	
	
	
	
	
}
