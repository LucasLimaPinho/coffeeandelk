package com.rest.cofeeandelk.common;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rest.cofeeandelk.entity.CarPromotion;
import com.rest.cofeeandelk.repository.CarPromotionElasticRepository;

// Dummy data for indexName "carPromotion" no Elasticsearch
// Objetivo: lidar com Global Handling Exception.

@Component
public class CarPromotionElasticDataSource {
	
	@Autowired
	private CarPromotionElasticRepository carPromotionElasticRepository;
	
	private static final Logger LOG = LoggerFactory.getLogger(CarPromotionElasticDataSource.class);
	
	
	// @EventListener(ApplicationReadyEvent.class) faz com que este m�todo rode toda vez que a aplica��o iniciar.
	
	@EventListener(ApplicationReadyEvent.class)
	public void populatePromotionData() {
		
		// Assim que a aplica��o iniciar, deletar todos os documentos do index carPromotion no Elasticsearch
		// Jeito mais simples de limpar o indice do Elasticsearch do que em compara��o com o formato utilizado
		// em CarElasticDataSource.java com utiliza��o de Spring WebClient
		
		carPromotionElasticRepository.deleteAll();
		
		var carPromotions = new ArrayList<CarPromotion>();
		
		var promotion1 = new CarPromotion();
		promotion1.setType("bonus");
		promotion1.setDescription("Compre em dinheiro e ganhe um jantar de luxo");
		
		var promotion2 = new CarPromotion();
		promotion2.setType("bonus");
		promotion2.setDescription("Compre em estalecas e ganhe um x-cora��o");
		
		var promotion3 = new CarPromotion();
		promotion3.setType("discount");
		promotion3.setDescription("Compre em bitcoins e ganhe um oompa-loompa");
		
		var promotion4 = new CarPromotion();
		promotion4.setType("discount");
		promotion4.setDescription("Compre em euros e ganhe um 1 milh�o em barras de ouro");
		
		var promotion5 = new CarPromotion();
		promotion5.setType("bonus");
		promotion5.setDescription("Compre em dolar e ganhe um espetinho");
		
		var promotion6 = new CarPromotion();
		promotion6.setType("bonus");
		promotion6.setDescription("Compre a vista e ganhe uma viagem para LaLaLand");
		
		var promotion7 = new CarPromotion();
		promotion7.setType("discount");
		promotion7.setDescription("Compre no d�bito e ganhe uma viagem para o Nordeste");
		
		var promotion8 = new CarPromotion();
		promotion8.setType("discount");
		promotion8.setDescription("N�o pague nada, sai de gra�a");
		
		var promotion9 = new CarPromotion();
		promotion9.setType("bonus");
		promotion9.setDescription("Compre em dinheiro e pague 2000 reais a mais");
		
		var promotion10 = new CarPromotion();
		promotion10.setType("bonus");
		promotion10.setDescription("Compre no cr�dito e ganhe uma sand�lia");
		
		carPromotions.add(promotion1);
		carPromotions.add(promotion2);
		carPromotions.add(promotion3);
		carPromotions.add(promotion4);
		carPromotions.add(promotion5);
		carPromotions.add(promotion6);
		carPromotions.add(promotion7);
		carPromotions.add(promotion8);
		carPromotions.add(promotion9);
		carPromotions.add(promotion10);
		
		carPromotionElasticRepository.saveAll(carPromotions);
		
		LOG.info("POJOS saved as documents in Elasticsearch thanks to Spring Data Elasticsearch :" + carPromotions);
		LOG.info("Quantity of dummy car promotions saved:" + carPromotionElasticRepository.count());
		
				
	}

}
