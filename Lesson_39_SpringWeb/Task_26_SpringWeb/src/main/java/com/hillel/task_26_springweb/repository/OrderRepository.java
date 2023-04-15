package com.hillel.task_26_springweb.repository;

import com.hillel.task_26_springweb.dto.OrderDto;
import com.hillel.task_26_springweb.entity.Order;
import com.hillel.task_26_springweb.entity.Product;
import com.hillel.task_26_springweb.exception.NoSuchOrderException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class OrderRepository {

    private final ModelMapper modelMapper;

    private final List<Order> orders;

    public Long save(Order order) {
        orders.add(order);

        return order.getId();
    }

    public OrderDto getById(Long id) {
        return modelMapper.map(checkOrder(id), OrderDto.class);
    }

    public List<OrderDto> getAll() {
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        Order order = checkOrder(id);

        orders.remove(order);
    }

    private Order checkOrder(Long id) {
        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchOrderException("Order with id " + id + " doesn't exist"));
    }

    @PostConstruct
    void initOrders() {
        BigDecimal sum;

        Product cola = new Product("CocaCola", new BigDecimal("9.99"));
        Product kontik = new Product("Super Kontik", new BigDecimal("5.45"));
        Product sprite = new Product("Sprite", new BigDecimal("12.15"));
        Product bud = new Product("Bud", new BigDecimal("9.37"));
        Product burger = new Product("Burger", new BigDecimal("8.12"));
        Product pizza = new Product("Pizza", new BigDecimal("14.99"));
        Product bulka = new Product("Bulka", new BigDecimal("1.58"));
        Product cake = new Product("Cake", new BigDecimal("28.87"));
        Product fish = new Product("Fish", new BigDecimal("85.11"));
        Product potato = new Product("Potato", new BigDecimal("2.48"));
        Product pasta = new Product("Pasta", new BigDecimal("10.85"));

        Order order1 = new Order();
        order1.getProducts().merge(cola, 5L, Long::sum);
        order1.getProducts().merge(bud, 10L, Long::sum);
        order1.getProducts().merge(pizza, 3L, Long::sum);
        order1.getProducts().merge(burger, 7L, Long::sum);

        sum = order1.getProducts().entrySet().stream()
                .map(prod -> prod.getKey().getCost()
                        .multiply(BigDecimal.valueOf(prod.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order1.setCost(sum);

        Order order2 = new Order();
        order2.getProducts().merge(sprite, 2L, Long::sum);
        order2.getProducts().merge(kontik, 8L, Long::sum);
        order2.getProducts().merge(cake, 1L, Long::sum);
        order2.getProducts().merge(pasta, 2L, Long::sum);
        order2.getProducts().merge(fish, 3L, Long::sum);

        sum = order2.getProducts().entrySet().stream()
                .map(prod -> prod.getKey().getCost()
                        .multiply(BigDecimal.valueOf(prod.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order2.setCost(sum);

        Order order3 = new Order();
        order3.getProducts().merge(bulka, 11L, Long::sum);
        order3.getProducts().merge(potato, 22L, Long::sum);
        order3.getProducts().merge(sprite, 3L, Long::sum);
        order3.getProducts().merge(cake, 5L, Long::sum);

        sum = order3.getProducts().entrySet().stream()
                .map(prod -> prod.getKey().getCost()
                        .multiply(BigDecimal.valueOf(prod.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order3.setCost(sum);

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
    }

    @Override
    public String toString() {
        return "OrderRepository{" +
                "orders=" + orders +
                '}';
    }
}
