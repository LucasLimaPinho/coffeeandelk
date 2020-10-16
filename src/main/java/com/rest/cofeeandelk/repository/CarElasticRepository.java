package com.rest.cofeeandelk.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.rest.cofeeandelk.entity.Car;

// A primeira classe de ElasticsearchRepository deve ser a classe a ser armazenada em data storage do Elasticsearch!!!

// A segunda � o field identificador de Car, ent�o usaremos String.


public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {
	
	

}
