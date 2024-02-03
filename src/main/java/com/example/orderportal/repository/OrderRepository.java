package com.example.orderportal.repository;

import com.example.orderportal.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderCode(String orderCode);
//    Optional<Object> findByCode(String code);
}
