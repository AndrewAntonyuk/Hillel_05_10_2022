package com.hillel.task_26_springweb.controller;

import com.hillel.task_26_springweb.dto.OrderDto;
import com.hillel.task_26_springweb.entity.Order;
import com.hillel.task_26_springweb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderRepository.getAll();
    }

    @GetMapping("/order/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderRepository.getById(id);
    }

    @PostMapping
    public Long createOrder() {
        return orderRepository.save(new Order());
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}
