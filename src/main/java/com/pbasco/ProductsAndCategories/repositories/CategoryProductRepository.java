package com.pbasco.ProductsAndCategories.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pbasco.ProductsAndCategories.models.CategoryProduct;

@Repository
public interface CategoryProductRepository extends CrudRepository <CategoryProduct, Long> {
}