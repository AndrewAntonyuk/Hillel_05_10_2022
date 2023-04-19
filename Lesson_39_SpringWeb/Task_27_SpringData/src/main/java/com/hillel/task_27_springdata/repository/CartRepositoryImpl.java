package com.hillel.task_27_springdata.repository;

import com.hillel.task_27_springdata.entity.Cart;
import com.hillel.task_27_springdata.entity.Product;
import com.hillel.task_27_springdata.exception.NoSuchCartException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Transactional
public class CartRepositoryImpl implements CartRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long create(Cart cart) {
        final String SQL_INSERT = "INSERT INTO carts() VALUES()";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                con -> {
                    return con.prepareStatement(SQL_INSERT, new String[]{"id"});
                }
                , keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Boolean deleteById(Long id) {
        final String SQL_DELETE_CHILD = "DELETE FROM carts_products WHERE cart_id = ?";
        jdbcTemplate.update(SQL_DELETE_CHILD, id);

        final String SQL_DELETE_PARENT = "DELETE FROM carts WHERE id = ?";
        return jdbcTemplate.update(SQL_DELETE_PARENT, id) > 0;
    }

    @Override
    public Cart getById(Long id) {
        final String SQL_FIND_BY_ID_CART = "SELECT * FROM carts WHERE id = ?";
        final String SQL_FIND_BY_CART_ID_PRODUCTS = "SELECT p.* FROM products AS p" +
                " INNER JOIN carts_products AS cp ON p.id = cp.products_id" +
                " WHERE cp.cart_id = ?";

        List<Product> products = jdbcTemplate.query(SQL_FIND_BY_CART_ID_PRODUCTS, (resultSet) -> {
            List<Product> productList = new ArrayList<>();

            while (resultSet.next()) {
                productList.add(new Product(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("cost")));
            }

            return productList;
        }, id);

        Cart cart = jdbcTemplate.query(SQL_FIND_BY_ID_CART, (resultSet) -> {
            Cart foundCart = null;

            if (resultSet.next()) {
                foundCart = new Cart(resultSet.getLong("id"));
            }

            return foundCart;
        }, id);

        checkCart(cart, id);

        cart.setProducts(products);

        return cart;
    }

    private void checkCart(Cart cart, Long id) {
        if (cart == null) {
            throw new NoSuchCartException("Can't find cart with id " + id);
        }
    }
}
