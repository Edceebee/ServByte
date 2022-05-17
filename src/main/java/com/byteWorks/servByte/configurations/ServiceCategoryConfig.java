package com.byteWorks.servByte.configurations;

import com.byteWorks.servByte.models.Cities;
import com.byteWorks.servByte.models.ServiceProvider;
import com.byteWorks.servByte.models.Meals;
import com.byteWorks.servByte.models.ServiceCategory;
import com.byteWorks.servByte.repository.ServiceProviderRepository;
import com.byteWorks.servByte.repository.MealsRepository;
import com.byteWorks.servByte.repository.ServiceCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * This class seeds the database using command line runner.
 */
@Configuration
public class ServiceCategoryConfig {

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner(ServiceCategoryRepository serviceCategoryRepository, ServiceProviderRepository serviceProviderRepository,
                                        MealsRepository mealsRepository) {
        return (args) -> {
            ServiceCategory serviceCategory1 = new ServiceCategory(null, new ArrayList<>(), "laundry", "pharmacy",
                    "grocery");

            ServiceProvider serviceProvider1 = new ServiceProvider(null, "Stella Restaurant", "stellarest@gmail.com",
                    "0909090909", Cities.Abuja, new ArrayList<>(), serviceCategory1);
            ServiceProvider serviceProvider2 = new ServiceProvider(null, "Neme Restaurant", "nemerest@gmail.com",
                    "080808080", Cities.Lagos, new ArrayList<>(), serviceCategory1);
            List<ServiceProvider> serviceProviderList = new ArrayList<>();
            serviceProviderList.add(serviceProvider1);
            serviceProviderList.add(serviceProvider2);

            serviceProvider1.setServiceCategory(serviceCategory1);
            serviceProvider2.setServiceCategory(serviceCategory1);
            serviceCategory1.setServiceProviders(serviceProviderList);

            serviceCategoryRepository.save(serviceCategory1);

            serviceProviderRepository.save(serviceProvider1);
            serviceProviderRepository.save(serviceProvider2);


            Meals meals1 = new Meals(null, "picture", 2000, "20mins", "Pounded Yam", serviceProvider1);
            Meals meals2 = new Meals(null, "picture", 1500, "20mins", "Jollof rice", serviceProvider2);
            List<Meals> mealsList = new ArrayList<>();
            mealsList.add(meals1);

            List<Meals> mealsList1 = new ArrayList<>();
            mealsList1.add(meals2);

            serviceProvider1.setMealsList(mealsList);
            serviceProvider2.setMealsList(mealsList1);

            meals1.setServiceProvider(serviceProvider1);
            meals2.setServiceProvider(serviceProvider2);

            mealsRepository.save(meals1);
            mealsRepository.save(meals2);

            serviceProviderRepository.save(serviceProvider1);
            serviceProviderRepository.save(serviceProvider2);

        };
    }
}
