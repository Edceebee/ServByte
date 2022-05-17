package com.byteWorks.servByte.repository;

import com.byteWorks.servByte.models.PlaceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceOrderRepository extends JpaRepository<PlaceOrder, Long> {

}
