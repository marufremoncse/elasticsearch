package com.codingsense.elasticsearch.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingsense.elasticsearch.model.Product;
import com.codingsense.elasticsearch.repo.ProductRepo;
import com.codingsense.elasticsearch.service.ElasticSearchService;
import com.codingsense.elasticsearch.service.ProductService;

import co.elastic.clients.elasticsearch.core.SearchResponse;

@RestController
@RequestMapping("products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ElasticSearchService elasticSearchService;
	
	@PostMapping
	public Product insertProduct(@RequestBody Product product) {
		return productService.insertProduct(product);
	}
	
	@GetMapping("{id}")
	public Product getProduct(@PathVariable int id) {
		return productService.getProduct(id);
	}
	
	@GetMapping
	public Iterable<Product> getProducts() {
		return productService.getProducts();
	}
	
	@PutMapping("{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable int id) {
		return productService.updateProduct(product, id);
	}
	
	@DeleteMapping("{id}")
	public void deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
	}
	
	@GetMapping("match-all")
	public String matchAll() {
		return elasticSearchService.getSearchResponse().toString();
	}
	
	@GetMapping("match-all-products")
	public String matchAllProducts() {
		return elasticSearchService.getSearchResponse().toString();
	}
}
