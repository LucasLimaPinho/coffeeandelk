package com.rest.cofeeandelk.api.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.cofeeandelk.entity.CarPromotion;
import com.rest.cofeeandelk.exception.IllegalApiParamException;
import com.rest.cofeeandelk.repository.CarPromotionElasticRepository;
import com.rest.cofeeandelk.service.CarPromotionService;

@RestController
@RequestMapping(value = "/api/car/v1")
public class CarPromotionRESTApi {

	@Autowired
	private CarPromotionService carPromotionService;

	@Autowired
	private CarPromotionElasticRepository carPromotionElasticRepository;

	@GetMapping(value = "/promotions")
	public List<CarPromotion> listAvailablePromotions(@RequestParam(name = "type") String promotionType,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {

		// Inicialmente iremos checar se o tipo de promoção dada como query parameter é
		// válida utilizando o método
		// isValidPromotionType da interface CarPromotionService e implementada por
		// DefaultCarPromotionService

		if (!carPromotionService.isValidPromotionType(promotionType)) {

			throw new IllegalApiParamException("The promotion type is not valid :" + promotionType);

		}

		var pageable = PageRequest.of(page, size);

		return carPromotionElasticRepository.findByType(promotionType, pageable).getContent();

	}

}
