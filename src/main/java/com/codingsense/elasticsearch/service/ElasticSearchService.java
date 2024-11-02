package com.codingsense.elasticsearch.service;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingsense.elasticsearch.model.Product;
import com.codingsense.elasticsearch.util.ElasticSearchUtil;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;

@Service
public class ElasticSearchService {
	@Autowired
	ElasticsearchClient elasticsearchClient;
	
	public SearchResponse<Map> getSearchResponse(){
		Supplier<Query> supplier = ElasticSearchUtil.supplier();
		SearchResponse<Map> searchResponse = null;
		try {
			searchResponse = elasticsearchClient.search(s -> s.query(supplier.get()), Map.class);
			System.out.println("MatchAllQuery: " + supplier.get());
			System.out.println("Search Response: "+ searchResponse);
		} catch (ElasticsearchException | IOException e) {
			e.printStackTrace();
		}
		
		return searchResponse;
	}
	
	public SearchResponse<Product> getSearchResponseForProducts(){
		Supplier<Query> supplier = ElasticSearchUtil.supplier();
		SearchResponse<Product> searchResponse = null;
		try {
			searchResponse = elasticsearchClient.search(s -> s.index("products").query(supplier.get()), Product.class);
			System.out.println("MatchAllQueryForProducts: " + supplier.get());
			System.out.println("Search Response for products: " + searchResponse);
		} catch (ElasticsearchException | IOException e) {
			e.printStackTrace();
		}
		
		return searchResponse;
	}
}
