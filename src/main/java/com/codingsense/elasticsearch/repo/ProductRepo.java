package com.codingsense.elasticsearch.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.codingsense.elasticsearch.model.Product;

public interface ProductRepo extends ElasticsearchRepository<Product, Integer>{

}
