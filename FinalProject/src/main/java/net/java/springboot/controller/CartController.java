package net.java.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.java.springboot.Global.Global;
import net.java.springboot.model.Product;
import net.java.springboot.service.ProductService;

@Controller

public class CartController {

	@Autowired
	ProductService productService;
	@GetMapping("addToCart/{id}")
	public String addToCart(@PathVariable int id)
	{
		Global.cart.add(productService.getProductById(id).get());
		
		return "redirect:/shop";
	}
	
	
	@GetMapping("/cart")
	public String cartGet(Model model)
	{
		model.addAttribute("cartCount", Global.cart.size());
		model.addAttribute("total", Global.cart.stream().mapToDouble(Product::getPrice).sum());	
		model.addAttribute("cart", Global.cart);
		
		return "cart";
		
	
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model)
	{
		model.addAttribute("total", Global.cart.stream().mapToDouble(Product::getPrice).sum());	

		
		return "checkout";
		
	}
	
	@PostMapping("/payNow")
	public String orderPlaced(Model model)
	{
		model.addAttribute("cartCount", Global.cart.size());
		model.addAttribute("total", Global.cart.stream().mapToDouble(Product::getPrice).sum());	
		model.addAttribute("parameters");
		model.addAttribute("entry.key");

		
		
		return "orderPlaced";
		
		
	}
	

	
	
}
