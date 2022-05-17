package com.byteWorks.servByte;

import com.byteWorks.servByte.models.*;
import com.byteWorks.servByte.repository.MealsRepository;
import com.byteWorks.servByte.repository.PlaceOrderRepository;
import com.byteWorks.servByte.repository.ServiceCategoryRepository;
import com.byteWorks.servByte.repository.ServiceProviderRepository;
import com.byteWorks.servByte.service.ServiceCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
@SpringBootTest
class ServByteApplicationTests {

	@Autowired
	ServiceCategoryRepository serviceCategoryRepository;

	@Autowired
	ServiceProviderRepository serviceProviderRepository;

	@Autowired
	MealsRepository mealsRepository;

	@Autowired
	PlaceOrderRepository placeOrderRepository;

	@Autowired
	ServiceCategoryService serviceCategoryService;

	@Test
	@Order(1)
	void testToGetListOfAllCategories() {
		int size = serviceCategoryRepository.findAll().size();
		log.info("Number of service categories --> {}", size);
		assertThat((long) serviceCategoryService.allCategories().size()).isEqualTo(size);
	}


	@Test
	void testToGetServiceProviderById() {
		ServiceProvider serviceProvider = new ServiceProvider();
		serviceProvider.setNameOfRestaurant("Jolly restaurant");
		serviceProvider.setEmail("jolly@gmail.com");
		serviceProvider.setPhoneNumber("0801112223");
		serviceProvider.setCity(Cities.Kano);

		serviceProviderRepository.save(serviceProvider);
		log.info("saved service provider --> {}", serviceProvider);

		assertThat(serviceProvider.getCity()).isEqualTo(Cities.Kano);
		assertThat(serviceProvider.getNameOfRestaurant()).isEqualTo("Jolly restaurant");
		assertThat(serviceProvider.getEmail()).isEqualTo("jolly@gmail.com");
		assertThat(serviceProvider.getPhoneNumber()).isEqualTo("0801112223");

		Optional<ServiceProvider> serviceProvider1 = serviceCategoryService.getServiceProviderById(serviceProvider.getId());
		assertThat(serviceProvider1).get().isNotNull();

	}

	@Test
	@Order(2)
	void testToGetServiceProviderByCity() {
		ServiceProvider serviceProvider01 = new ServiceProvider();
		serviceProvider01.setNameOfRestaurant("Jolly restaurant");
		serviceProvider01.setEmail("jolly@gmail.com");
		serviceProvider01.setPhoneNumber("0801112223");
		serviceProvider01.setCity(Cities.Kano);

		serviceProviderRepository.save(serviceProvider01);
		log.info("saved service provider --> {}", serviceProvider01);

		assertThat(serviceProvider01.getCity()).isEqualTo(Cities.Kano);
		assertThat(serviceProvider01.getNameOfRestaurant()).isEqualTo("Jolly restaurant");
		assertThat(serviceProvider01.getEmail()).isEqualTo("jolly@gmail.com");
		assertThat(serviceProvider01.getPhoneNumber()).isEqualTo("0801112223");

		Optional<ServiceProvider> serviceProvider1 = serviceCategoryService.serviceProviderByCity(serviceProvider01.getCity());
		assertThat(serviceProvider1).get().isNotNull();

	}

	@Test
	@Transactional
	void testToPlaceOrder() throws Exception {

		ServiceProvider serviceProvider = new ServiceProvider();
		serviceProvider.setNameOfRestaurant("Chief restaurant");
		serviceProvider.setEmail("chief@gmail.com");
		serviceProvider.setPhoneNumber("08765332349");
		serviceProvider.setCity(Cities.Asaba);

		Meals meals = new Meals();
		meals.setDescription("Yummy");
		meals.setNameOfMeal("Rice");
		meals.setPreparationTime("20mins");

		PlaceOrder placeOrder = new PlaceOrder(null, "Chief restaurant",
				"Doe", "doe@gmail.com", "434343445", "Lagos",
				"Rice");

		List<Meals> mealsList = new ArrayList<>();
		mealsList.add(meals);

		serviceProvider.setMealsList(mealsList);
		meals.setServiceProvider(serviceProvider);

		serviceProviderRepository.save(serviceProvider);
		mealsRepository.save(meals);
		placeOrderRepository.save(placeOrder);

		String order = serviceCategoryService.placeOrder(serviceProvider.getId(), placeOrder);
		assertThat(order).isNotNull();
	}

}
