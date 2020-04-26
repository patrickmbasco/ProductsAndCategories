package com.pbasco.ProductsAndCategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pbasco.ProductsAndCategories.models.Category;
import com.pbasco.ProductsAndCategories.models.CategoryProduct;
import com.pbasco.ProductsAndCategories.models.Product;
import com.pbasco.ProductsAndCategories.repositories.CategoryProductRepository;
import com.pbasco.ProductsAndCategories.repositories.CategoryRepository;
import com.pbasco.ProductsAndCategories.repositories.ProductRepository;

@Service
public class ProductCategoryServices {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final CategoryProductRepository categoryProductRepository;
	
	public ProductCategoryServices(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryProductRepository categoryProductRepo) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.categoryProductRepository = categoryProductRepo;
	}
	
	public void addProd(Product product) {
		productRepository.save(product);
	}
	
	public void setCat(Category category) {
		
	}
	
	public void addCatToProduct(CategoryProduct categoryProduct) {
		categoryProductRepository.save(categoryProduct);
	}
	
	public Product singleProd(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
		}
		else {
			return null;
		}
	}
	
	public Iterable<Product> availableProductsForCategory(Category category) {
		return productRepository.findByCategoriesNotContains(category);
	}
	
	public void addCat(Category category) {
		categoryRepository.save(category);
	}
	
	public List<Category> getCatAll() {
		return categoryRepository.findAll();
	}
	
    public List<Category> availableCategoriesForProduct(Product product) {
        return categoryRepository.findByProductsNotContains(product);  
    }
    
	public void addProdToCategory(CategoryProduct categoryProduct) {
		categoryProductRepository.save(categoryProduct);
	}
	
	public Category singleCat(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if(category.isPresent()) {
			return category.get();
		}
		else {
			return null;
		}
	}
}
