package com.byteWorks.servByte.service;

import com.byteWorks.servByte.models.Cities;
import com.byteWorks.servByte.models.PlaceOrder;
import com.byteWorks.servByte.models.ServiceCategory;
import com.byteWorks.servByte.models.ServiceProvider;

import java.util.List;
import java.util.Optional;

public interface ServiceCategoryService {
    List<ServiceCategory> allCategories();

    Optional<ServiceProvider> getServiceProviderById(Long id);

    String placeOrder(Long restaurantId, PlaceOrder order) throws Exception;

    Optional<ServiceProvider> serviceProviderByCity(Cities city);
}
