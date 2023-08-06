package com.hillel.online_shop.service.impl;

import com.hillel.online_shop.dto.order.OrderDTO;
import com.hillel.online_shop.entity.Cart;
import com.hillel.online_shop.entity.Order;
import com.hillel.online_shop.entity.Purchase;
import com.hillel.online_shop.entity.User;
import com.hillel.online_shop.repository.CartRepository;
import com.hillel.online_shop.repository.OrderRepository;
import com.hillel.online_shop.repository.PurchaseRepository;
import com.hillel.online_shop.repository.UserRepository;
import com.hillel.online_shop.service.CartService;
import com.hillel.online_shop.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderDTO findById(long id) {
        return modelMapper.map(orderRepository.findById(id), OrderDTO.class);
    }

    @Override
    public List<OrderDTO> findAllByUserId(long userId) {
        return orderRepository.findAllByUserId(userId)
                .stream().map(order -> modelMapper.map(order, OrderDTO.class))
                .toList();
    }

    @Override
    @Transactional
    public Long create(Long userId, OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.setBalance(user.getBalance().subtract(order.getCost()));
        userRepository.save(user);
        Cart cart = user.getCart();
        List<Purchase> purchases = cart.getPurchases();
        for (Purchase purchase : purchases) {
            purchase.setOrder(order);
            purchase.setCart(null);
            purchaseRepository.save(purchase);
        }
        order.setUser(user);
        return orderRepository.save(order).getId();
    }
}
