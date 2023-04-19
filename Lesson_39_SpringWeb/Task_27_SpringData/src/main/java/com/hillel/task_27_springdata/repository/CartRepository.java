package com.hillel.task_27_springdata.repository;

import com.hillel.task_27_springdata.entity.Cart;

public interface CartRepository {
    Long create(Cart cart);

    Boolean deleteById(Long id);

    Cart getById(Long id);
}
