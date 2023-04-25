package com.hillel.task28springdata.controller;

import com.hillel.task28springdata.dto.OrderDTO;
import com.hillel.task28springdata.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order/{id}")
    public OrderDTO getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public Long add(@RequestBody OrderDTO orderDTO) {
        return orderService.add(orderDTO);
    }

    @DeleteMapping("/order/{id}")
    public void deleteById(@PathVariable Long id) {
        orderService.remove(id);
    }
}
