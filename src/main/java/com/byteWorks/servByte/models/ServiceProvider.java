package com.byteWorks.servByte.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameOfRestaurant;
    private String email;
    private String phoneNumber;
    private Cities city;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Meals> mealsList = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private ServiceCategory serviceCategory;
}
