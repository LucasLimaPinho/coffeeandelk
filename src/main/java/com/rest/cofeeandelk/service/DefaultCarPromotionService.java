package com.rest.cofeeandelk.service;

import org.springframework.stereotype.Service;

// Aqui criamos um m�todo para checar se o tipo de promo��o � valido de acordo com a lista em carPromotionService
// A lista criada em carPromotionService � PROMOTION_TYPES list of bonus e discount;

@Service
public class DefaultCarPromotionService implements CarPromotionService {

	@Override
	public boolean isValidPromotionType(String promotionType) {
		// TODO Auto-generated method stub
		return PROMOTION_TYPES.contains(promotionType.toLowerCase());
	}

}
