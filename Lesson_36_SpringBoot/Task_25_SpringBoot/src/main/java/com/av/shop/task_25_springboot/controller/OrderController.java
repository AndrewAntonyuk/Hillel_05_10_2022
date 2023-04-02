package com.av.shop.task_25_springboot.controller;

import com.av.shop.task_25_springboot.dto.OrderDto;
import com.av.shop.task_25_springboot.entity.Order;
import com.av.shop.task_25_springboot.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;

    @PostMapping("/create")
    public Long create(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping(value = "/order/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return orderRepository.getById(id);
    }

    @GetMapping(value = "/orders")
    public List<OrderDto> getOrders() {
        return orderRepository.getAll();
    }

    @DeleteMapping(value = "/delete_order/{id}")
    public void delete(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}
