package com.pbasco.ProductsAndCategories.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pbasco.ProductsAndCategories.models.Category;
import com.pbasco.ProductsAndCategories.services.ProductCategoryServices;

@Controller
public class CategoryController {
	private final ProductCategoryServices productCategoryServices;
	
	public CategoryController(ProductCategoryServices productCategoryServices) {
		this.productCategoryServices = productCategoryServices;
	}
	
	@GetMapping("/categories/new")
	public String newCatPage(@ModelAttribute("categoryObject") Category category) {
		return "newcategory.jsp";
	}
	
	@PostMapping("/addcategory")
	public String addCategory(@Valid @ModelAttribute("categoryObject") Category category, BindingResult result) {
		if(result.hasErrors()) {
			return "/categories/new";
		}
		else {
			productCategoryServices.addCat(category);
			return "redirect:/categories/new";
		}
	}
}