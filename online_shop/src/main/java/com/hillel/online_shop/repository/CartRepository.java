package com.hillel.online_shop.repository;

import com.hillel.online_shop.entity.Cart;
import com.hillel.online_shop.entity.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);
}
