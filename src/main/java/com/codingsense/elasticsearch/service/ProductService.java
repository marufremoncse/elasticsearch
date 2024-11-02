package com.codingsense.elasticsearch.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingsense.elasticsearch.model.Product;
import com.codingsense.elasticsearch.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;
	
	public Product insertProduct(Product product) {
		return productRepo.save(product);
	}
	
	public Product getProduct(int id) {
		return productRepo.findById(id).get();
	}
	
	public Iterable<Product> getProducts() {
		return productRepo.findAll();
	}
	
	public Product updateProduct(Product product, int id) {
		Optional<Product> existingProduct = productRepo.findById(id);
		if(existingProduct.isPresent()) {
			Product productToUpdate = existingProduct.get();
			productToUpdate.setName(product.getName());
			productToUpdate.setPrice(product.getPrice());
			return productRepo.save(productToUpdate);
		}
		return null;
	}

	public void deleteProduct(int id) {
		productRepo.deleteById(id);
	}
}
