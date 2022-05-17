package com.byteWorks.servByte.repository;

import com.byteWorks.servByte.models.Meals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealsRepository extends JpaRepository<Meals, Long> {
    Optional<Meals> findByNameOfMeal(String nameOfMeal);
}
