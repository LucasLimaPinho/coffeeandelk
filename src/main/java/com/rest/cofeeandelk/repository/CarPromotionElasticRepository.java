package com.rest.cofeeandelk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.rest.cofeeandelk.entity.CarPromotion;

// Remember: Primeiro type no ElasticsearchRepository é o POJO a ser armazenado como documento nos shards do 
// Elasticsearch e o segundo é o type do identificador do documento - quase sempre String.


@Repository
public interface CarPromotionElasticRepository extends ElasticsearchRepository<CarPromotion, String> {
	
	// Query methods provided by Spring Data implemented in interfaces.
	public Page<CarPromotion> findByType(String type, Pageable pageable);
	

}
