package com.rest.cofeeandelk.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.rest.cofeeandelk.entity.Car;

// A primeira classe de ElasticsearchRepository deve ser a classe a ser armazenada em data storage do Elasticsearch!!!

// A segunda é o field identificador de Car, então usaremos String.

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {
	
	

}
