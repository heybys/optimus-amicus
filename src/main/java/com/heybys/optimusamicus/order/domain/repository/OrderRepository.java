package com.heybys.optimusamicus.order.domain.repository;

import com.heybys.optimusamicus.order.domain.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, CustomOrderRepository {

  List<Order> findByName(String name);
}