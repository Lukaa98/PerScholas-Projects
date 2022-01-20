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
import net.java.springboot.model.Category;
import net.java.springboot.model.Product;
import net.java.springboot.service.CategoryService;
import net.java.springboot.service.ProductService;

@Controller
public class AdminController {
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
		@Autowired  //to declear dependecy 
	    private	CategoryService categoryService;
		@Autowired
		private ProductService productService;
		@GetMapping ("/admin")
		public String adminHome()
		{
			return "adminHome";
		}
		
		@GetMapping ("/admin/categories")
		public String getCat(Model model)
		{
			model.addAttribute("categories", categoryService.getAllCategory());
			return "categories";
		}
		
		@GetMapping ("/admin/categories/add")
		public String getCatAdd(Model model)
		{	
			model.addAttribute("category", new Category());
			return "categoriesAdd";
		}
		
		@PostMapping ("/admin/categories/add")
		public String postCatAdd(@ModelAttribute("category") Category category)
		//The @ModelAttribute is an annotation that binds a method parameter or method 
		//return value to a named model attribute and then exposes it to a web view. 
		{	
			categoryService.addCategory(category);
			return "redirect:/admin/categories";
		}
		
		@GetMapping("/admin/categories/delete/{id}")
		
			public String deleteCat(@PathVariable int id)//annotation binds a URI template
														//	variable to a method parameter in a controller.
			{
			categoryService.removeCategoryById(id);
			return "redirect:/admin/categories";
			
			}
		
		
		
		@GetMapping("/admin/categories/update/{id}")
		public String updateCat(@PathVariable int id, Model model)
		{
			
		Optional<Category> category = categoryService.getCategoryById(id);	
		
			if(category.isPresent())
			{
				model.addAttribute("category", category.get());
				return "categoriesAdd";
			}
			else
			{
				throw new RuntimeException(" Category not found for id : " + id);
			}		
			
		}
		
		
		//product section
		
		
		@GetMapping("/admin/products")
		public String products(Model model)
		{
		model.addAttribute("products" , productService.getAllProduct());
		return "products";
		
		}
		
		
		@GetMapping("/admin/products/add")
		public String productsAddGet(Model model)
		{
		model.addAttribute("productDTO" , new ProductDTO());
		model.addAttribute("categories" , categoryService.getAllCategory());
		return "productsAdd";
		 
		}
		
		
		@PostMapping("/admin/products/add")
		public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
				@RequestParam("productImage")MultipartFile file,
				@RequestParam("imgName")String imgName) throws IOException
		{
			
			
			//we need to convert proudctDTO object into product object
			Product product = new Product();
			product.setId(productDTO.getId());
			product.setName(productDTO.getName());
			product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
			product.setPrice(productDTO.getPrice());
			product.setWeight(productDTO.getWeight());
			product.setDescription(productDTO.getDescription());
			String imageUUID;
			if(!file.isEmpty())
			{
				imageUUID = file.getOriginalFilename();
				Path fileNameAndPath = (Path) Paths.get(uploadDir, imageUUID);
				try {
					Files.write(fileNameAndPath, file.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			else {
				imageUUID = imgName;
			}
			product.setImageName(imageUUID);
			productService.addProduct(product);
			
			
			return "redirect:/admin/products";
		}
		
		
		
		
		@GetMapping("/admin/product/delete/{id}")
		
			public String deleteProduct(@PathVariable long id)
			{
			productService.removeProductById(id);
			return "redirect:/admin/products";
			
			}
		
		
		
		@GetMapping("/admin/product/update/{id}")
		public String updateProductGet(@PathVariable long id, Model model)
		{
		
			Product product = productService.getProductById(id).get();
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setCategoryId((product.getCategory().getId()));
			productDTO.setPrice(product.getPrice());
			productDTO.setWeight(product.getWeight());
			productDTO.setDescription(product.getDescription());
			productDTO.setImageName(product.getImageName());
			
			model.addAttribute("categories", categoryService.getAllCategory());
			model.addAttribute("productDTO" , productDTO);
			
			return "productsAdd";
			
			
			
			
		}
		
			
	}
		

		
			

