package com.hillel.task28springdata.service.impl;

import com.hillel.task28springdata.dto.OrderDTO;
import com.hillel.task28springdata.entity.Order;
import com.hillel.task28springdata.entity.Product;
import com.hillel.task28springdata.exception.NoSuchOrderException;
import com.hillel.task28springdata.repository.OrderRepository;
import com.hillel.task28springdata.service.OrderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    @Override
    public OrderDTO getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchOrderException("Can't find order with id " + id));

        return modelMapper.map(order, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getAll() {
        List<Order> orders = (List<Order>) orderRepository.findAll();

        return orders.stream()
                .map(orderSup -> modelMapper.map(orderSup, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long add(OrderDTO order) {
        Order map = modelMapper.map(order, Order.class);

        return orderRepository.save(map).getId();
    }

    @Override
    public void remove(Long id) {
        checkOrder(id);

        orderRepository.deleteById(id);
    }

    private void checkOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new NoSuchOrderException("Can't find order with id " + id);
        }
    }

    @PostConstruct
    private void init() {
//        //Call once for create
//        BigDecimal sum;
//
//        Product cola;
//        Product kontik;
//        Product sprite;
//        Product bud;
//        Product burger;
//        Product pizza;
//        Product bulka;
//        Product cake;
//        Product fish;
//        Product potato;
//        Product pasta;
//
//        cola = new Product("CocaCola", new BigDecimal("9.99"));
//        kontik = new Product("Super Kontik", new BigDecimal("5.45"));
//        sprite = new Product("Sprite", new BigDecimal("12.15"));
//        bud = new Product("Bud", new BigDecimal("9.37"));
//        burger = new Product("Burger", new BigDecimal("8.12"));
//        pizza = new Product("Pizza", new BigDecimal("14.99"));
//        bulka = new Product("Bulka", new BigDecimal("1.58"));
//        cake = new Product("Cake", new BigDecimal("28.87"));
//        fish = new Product("Fish", new BigDecimal("85.11"));
//        potato = new Product("Potato", new BigDecimal("2.48"));
//        pasta = new Product("Pasta", new BigDecimal("10.85"));
//
//        productRepository.save(cola);
//        productRepository.save(kontik);
//        productRepository.save(sprite);
//        productRepository.save(bud);
//        productRepository.save(burger);
//        productRepository.save(bulka);
//        productRepository.save(cake);
//        productRepository.save(fish);
//        productRepository.save(potato);
//        productRepository.save(pasta);
//        productRepository.save(pizza);
//
//        cola = productRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("Illegal id for product repository"));
//        kontik = productRepository.findById(2L).orElseThrow(() -> new IllegalArgumentException("Illegal id for product repository"));
//        sprite = productRepository.findById(3L).orElseThrow(() -> new IllegalArgumentException("Illegal id for product repository"));
//        bud = productRepository.findById(4L).orElseThrow(() -> new IllegalArgumentException("Illegal id for product repository"));
//        burger = productRepository.findById(5L).orElseThrow(() -> new IllegalArgumentException("Illegal id for product repository"));
//        bulka = productRepository.findById(6L).orElseThrow(() -> new IllegalArgumentException("Illegal id for product repository"));
//        cake = productRepository.findById(7L).orElseThrow(() -> new IllegalArgumentException("Illegal id for product repository"));
//        fish = productRepository.findById(8L).orElseThrow(() -> new IllegalArgumentException("Illegal id for product repository"));
//        potato = productRepository.findById(9L).orElseThrow(() -> new IllegalArgumentException("Illegal id for product repository"));
//        pasta = productRepository.findById(10L).orElseThrow(() -> new IllegalArgumentException("Illegal id for product repository"));
//        pizza = productRepository.findById(11L).orElseThrow(() -> new IllegalArgumentException("Illegal id for product repository"));
//
//        Order order1 = new Order();
//        order1.setProducts(new ArrayList<>());
//        order1.getProducts().add(cola);
//        order1.getProducts().add(bud);
//        order1.getProducts().add(fish);
//        order1.getProducts().add(pasta);
//        order1.getProducts().add(kontik);
//
//        sum = order1.getProducts().stream()
//                .map(Product::getCost)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        order1.setCost(sum);
//
//
//        Order order2 = new Order();
//        order2.setProducts(new ArrayList<>());
//        order2.getProducts().add(sprite);
//        order2.getProducts().add(burger);
//        order2.getProducts().add(bulka);
//        order2.getProducts().add(cake);
//        order2.getProducts().add(potato);
//        order2.getProducts().add(pizza);
//
//        sum = order2.getProducts().stream()
//                .map(Product::getCost)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        order2.setCost(sum);
//
//        orderRepository.save(order1);
//        orderRepository.save(order2);
    }
}
