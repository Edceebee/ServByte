package com.byteWorks.servByte.service;

import com.byteWorks.servByte.models.Cities;
import com.byteWorks.servByte.models.ServiceCategory;
import com.byteWorks.servByte.models.ServiceProvider;
import com.byteWorks.servByte.models.PlaceOrder;

import com.byteWorks.servByte.repository.ServiceProviderRepository;
import com.byteWorks.servByte.repository.PlaceOrderRepository;
import com.byteWorks.servByte.repository.ServiceCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    PlaceOrderRepository placeOrderRepository;

    /**
     * This method retrieves all the categories saved in the database.
     * @return a list of categories saved.
     */
    @Override
    public List<ServiceCategory> allCategories() {
        return serviceCategoryRepository.findAll();
    }

    /**
     * This method retrieves a service provider saved in the database based on the id provided.
     * @param id for identifying the service provider to retrieve.
     * @return a list of categories saved.
     */
    @Override
    public Optional<ServiceProvider> getServiceProviderById(Long id) {
        Optional<ServiceProvider> findById = Optional.ofNullable(serviceProviderRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("id with " + id+" not found")));
        return findById;
    }


    /**
     * This method retrieves a service provider with city given.
     * @param city used for identifying which city service providers are.
     * @return service provider with city given.
     */
    @Override
    public Optional<ServiceProvider> serviceProviderByCity(Cities city) {
        Optional<ServiceProvider> byCity = Optional.ofNullable(serviceProviderRepository.findByCity(city).
                orElseThrow(() -> new IllegalArgumentException("City not found")));
        return byCity;
    }


    /**
     * This is the method used for placing an order.
     * @param serviceProviderId used in identifying the service provider
     * @param order is the body used in placing the order
     * @return a String
     * @throws Exception if an error occurred somewhere
     */
    @Override
    public String placeOrder(Long serviceProviderId, PlaceOrder order) throws Exception {
        Optional<ServiceProvider> findById = Optional.ofNullable(serviceProviderRepository.findById(serviceProviderId).
                orElseThrow(() -> new IllegalArgumentException("id with " + serviceProviderId + " not found")));

        String orderRestaurant = order.getNameOfRestaurant();
        String orderMeal = order.getNameOfMeal();

        if (!(Objects.equals(orderRestaurant, findById.get().getNameOfRestaurant()))) {
            throw new Exception("Sorry!!! Service Provider does not have "+orderRestaurant+ " registered");
        }
        if (!(Objects.equals(orderMeal, findById.get().getMealsList().stream().findAny().get().getNameOfMeal()))) {
            throw new Exception("Sorry!!! Service Provider does not have "+orderMeal+ " registered");
        }

        placeOrderRepository.save(order);
//        sendEmail(order.getCustomerEmail());

        return "Order Placed Successfully";
    }

    /**
     * This is a method that sends confirmation mails
     * @param emailAddress is the email address the mail will be sent to.
     */
    public void sendEmail(String emailAddress) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailAddress);

        msg.setSubject("Confirmation mail");
        msg.setText("Your order has been received successfully \n Thank you");

        javaMailSender.send(msg);

    }
}
