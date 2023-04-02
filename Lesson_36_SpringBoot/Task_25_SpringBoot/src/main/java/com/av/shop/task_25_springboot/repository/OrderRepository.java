package com.av.shop.task_25_springboot.repository;

import com.av.shop.task_25_springboot.dto.OrderDto;
import com.av.shop.task_25_springboot.entity.Order;
import com.av.shop.task_25_springboot.exception.NoSuchOrderException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Long save(Order order) {
        return entityManager.merge(order).getId();
    }

    public OrderDto getById(Long id) {
        return mapOrder(checkOrder(id));
    }

    public List<OrderDto> getAll() {
        CriteriaQuery<Order> criteria = entityManager.getCriteriaBuilder().createQuery(Order.class);
        criteria.select(criteria.from(Order.class));

        List<Order> orders = entityManager.createQuery(criteria).getResultList();

        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id) {
        Order order = checkOrder(id);

        entityManager.remove(order);
    }

    private Order mapOrder(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }

    private OrderDto mapOrder(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    private Order checkOrder(Long id) {
        Order order = entityManager.find(Order.class, id);

        if (order == null) {
            throw new NoSuchOrderException("Order with id " + id + " doesn't exist");
        }

        return order;
    }
}
