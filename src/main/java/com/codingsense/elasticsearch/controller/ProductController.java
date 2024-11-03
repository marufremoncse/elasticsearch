package com.codingsense.elasticsearch.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
import co.elastic.clients.elasticsearch.core.search.Hit;

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
	public List<Product> matchAllProducts() {
		  SearchResponse<Product> searchResponseForProducts = elasticSearchService.getSearchResponseForProducts();
		  List<Hit<Product>> listOfHits = searchResponseForProducts.hits().hits();
		  List<Product> listOfProducts = listOfHits.stream().map(q -> q.source()).collect(Collectors.toCollection(ArrayList::new));
		  return listOfProducts;
	}
	
	@GetMapping("match-products-with-name/{name}")
	public List<Product> matchProductsWithListName(@PathVariable String name) {
		SearchResponse<Product> searchResponseForProducts = elasticSearchService.getSearchResponseForProductsWithField(name);
		List<Hit<Product>> listOfHits = searchResponseForProducts.hits().hits();
		List<Product> listOfProducts = listOfHits.stream().map(q -> q.source()).collect(Collectors.toCollection(ArrayList::new));
		return listOfProducts;
	}
}
