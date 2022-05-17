package com.byteWorks.servByte.models.dto;

import lombok.Data;

@Data
public class PlaceOrderDto {

    private String nameOfRestaurant;

    private String customerName;

    private String customerEmail;

    private String customerPhoneNumber;

    private String city;

    private String nameOfMeal;
}
