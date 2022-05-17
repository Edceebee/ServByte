package com.byteWorks.servByte.repository;

import com.byteWorks.servByte.models.Cities;
import com.byteWorks.servByte.models.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
    Optional<ServiceProvider> findByNameOfRestaurant(String name);
    Optional<ServiceProvider> findByCity(Cities city);
}

