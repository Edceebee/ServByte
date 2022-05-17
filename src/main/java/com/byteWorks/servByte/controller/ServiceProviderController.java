package com.byteWorks.servByte.controller;

import com.byteWorks.servByte.models.Cities;
import com.byteWorks.servByte.models.ServiceProvider;
import com.byteWorks.servByte.models.PlaceOrder;
import com.byteWorks.servByte.models.ServiceCategory;
import com.byteWorks.servByte.models.dto.PlaceOrderDto;
import com.byteWorks.servByte.responses.ApiResponse;
import com.byteWorks.servByte.service.ServiceCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ServiceProviderController {
    @Autowired
    ServiceCategoryService serviceCategoryService;

    @Autowired
    ModelMapper modelMapper;

    /**
     * This endpoint retrieves all the categories saved in the database.
     * @return a list of categories saved.
     */
    @GetMapping("/categories")
    public List<ServiceCategory> returnAllCategories() {
        return serviceCategoryService.allCategories();
    }

    /**
     * This endpoint retrieves a service provider saved in the database based on the id provided.
     * @param id for identifying the service provider to retrieve.
     * @return a list of categories saved.
     */
    @GetMapping("/serviceProvider/{id}")
    public ResponseEntity<?> getServiceProvider(@PathVariable Long id) {
        try{
            Optional<ServiceProvider> mealsList = serviceCategoryService.getServiceProviderById(id);
            return new ResponseEntity<>(mealsList, HttpStatus.FOUND);
        }
        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(new ApiResponse(exception.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This endpoint retrieves a service provider with city given.
     * @param city used for identifying which city service providers are.
     * @return service provider with city given.
     */
    @GetMapping("/serviceProvider/city/{city}")
    public ResponseEntity<?> getServiceProvider(@RequestParam("city") Cities city) {
        try{
            Optional<ServiceProvider> mealsList = serviceCategoryService.serviceProviderByCity(city);
            return new ResponseEntity<>(mealsList, HttpStatus.FOUND);
        }
        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(new ApiResponse(exception.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Endpoint used for placing an order.
     * @param restaurantId used in identifying the service provider
     * @param placeOrderDto is the body used in placing the order
     * @return a String
     * @throws Exception if an error occurred somewhere
     */
    @PostMapping("/order")
    public ResponseEntity<?> placeOrder(@RequestParam Long restaurantId, @RequestBody PlaceOrderDto placeOrderDto) throws Exception {
        try {
            PlaceOrder placeOrderRequest = modelMapper.map(placeOrderDto, PlaceOrder.class);
            String placeOrder = serviceCategoryService.placeOrder(restaurantId, placeOrderRequest);

            return new ResponseEntity<>(placeOrder, HttpStatus.CREATED);
        }

        catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(exception.getMessage()),
                    HttpStatus.EXPECTATION_FAILED);
        }
    }
}
