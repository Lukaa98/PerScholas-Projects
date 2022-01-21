package net.java.springboot.Luka.Shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.java.springboot.Luka.Shop.Global.Global;
import net.java.springboot.Luka.Shop.service.CategoryService;
import net.java.springboot.Luka.Shop.service.ProductService;



@Controller
public class HomeController {
	@Autowired  //to declear dependecy 
    private	CategoryService categoryService;
	@Autowired
	private ProductService productService;

	
	
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
