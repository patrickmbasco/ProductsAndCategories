package com.pbasco.ProductsAndCategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pbasco.ProductsAndCategories.models.Category;
import com.pbasco.ProductsAndCategories.models.Product;

@Repository
public interface ProductRepository extends CrudRepository <Product, Long> {
	List<Product> findAll();
	List<Product> findByCategoriesNotContains(Category category);
}