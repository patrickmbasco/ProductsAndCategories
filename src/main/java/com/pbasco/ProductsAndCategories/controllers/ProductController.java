package com.pbasco.ProductsAndCategories.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pbasco.ProductsAndCategories.models.Category;
import com.pbasco.ProductsAndCategories.models.CategoryProduct;
import com.pbasco.ProductsAndCategories.models.Product;
import com.pbasco.ProductsAndCategories.services.ProductCategoryServices;

@Controller
public class ProductController {
	private final ProductCategoryServices productCategoryServices;

	public ProductController(ProductCategoryServices productCategoryServices) {
		this.productCategoryServices = productCategoryServices;
	}
	
	@GetMapping("/products/new")
	public String newProdPage(@ModelAttribute("productObject") Product product) {
		return "newproduct.jsp";
	}
	
	@PostMapping("/addproduct")
	public String addProduct(@Valid @ModelAttribute("productObject") Product product, BindingResult result) {
		if(result.hasErrors()) {
			return "newproduct.jsp";
		}
		else {
			productCategoryServices.addProd(product);
			return "redirect:/products/new";
		}
	}
	
	@GetMapping("products/{prodid}")
	public String showProduct(@PathVariable("prodid") Long prodid, @ModelAttribute("categoryProductObj") CategoryProduct categoryProduct, Model model) {
		Product product = productCategoryServices.singleProd(prodid);
		model.addAttribute("product", product);
		model.addAttribute("categories", productCategoryServices.availableCategoriesForProduct(product));
		return "showproduct.jsp";
	}
	
	@PostMapping("/attachcategory")
	public String attachCategory(@ModelAttribute("categoryProductObj") CategoryProduct categoryProduct) {
		System.out.println(categoryProduct.getCategory().getName());
		System.out.println(categoryProduct.getProduct().getName());
		productCategoryServices.addCatToProduct(categoryProduct);
		return "redirect:/products/" + categoryProduct.getProduct().getId();
	}
	
	@GetMapping("categories/{catid}")
	public String showCategory(@PathVariable("catid") Long catid, @ModelAttribute("categoryProductObj") CategoryProduct categoryProduct, Model model) {
		Category category = productCategoryServices.singleCat(catid);
		model.addAttribute("category", category);
		model.addAttribute("products", productCategoryServices.availableProductsForCategory(category));
		return "showcategory.jsp";
	}
	
	@PostMapping("/attachproduct")
	public String attachProduct(@ModelAttribute("categoryProductObj") CategoryProduct categoryProduct) {
		System.out.println(categoryProduct.getCategory().getName());
		System.out.println(categoryProduct.getProduct().getName());
		productCategoryServices.addProdToCategory(categoryProduct);
		return "redirect:/categories/" + categoryProduct.getCategory().getId();
	}
	
	
}
