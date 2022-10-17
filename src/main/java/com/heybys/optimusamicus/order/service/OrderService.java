package com.heybys.optimusamicus.order.service;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.model.Coffee;
import com.heybys.optimusamicus.order.model.Customer;
import com.heybys.optimusamicus.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@LogExecutionTime
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  private final Customer customer;

  public void order(String menuName) {
    Coffee coffee = customer.order(menuName);

    log.info("Ordered coffee. {}", coffee);
  }

  @Transactional
  public Order retrieveOrder(Long orderId) {
    Order order = orderRepository.findById(orderId).orElseThrow();
    order.refreshSerialNumber();
    return order;
  }

  @Transactional
  public Order createOrder(Order order) {
    return orderRepository.save(order);
  }
}
