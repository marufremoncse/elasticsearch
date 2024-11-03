package com.codingsense.elasticsearch.util;

import java.util.function.Supplier;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

public class ElasticSearchUtil {
	public static Supplier<Query> supplier() {
		Supplier<Query> supplier = () -> Query.of(q -> q.matchAll(matchAllQuery()));
		return supplier;
	}

	public static MatchAllQuery matchAllQuery() {
		return new MatchAllQuery.Builder().build();
	}

	public static Supplier<Query> supplierWithNameField(String fieldValue) {
		Supplier<Query> supplier = () -> Query.of(q -> q.match(matchQueryWillNameField(fieldValue)));
		return supplier;
	}

	public static MatchQuery matchQueryWillNameField(String fieldValue) {
		return new MatchQuery.Builder().field("name").query(fieldValue).build();
	}
}
