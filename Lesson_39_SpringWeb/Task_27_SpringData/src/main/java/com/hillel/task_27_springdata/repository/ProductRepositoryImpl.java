package com.hillel.task_27_springdata.repository;

import com.hillel.task_27_springdata.entity.Product;
import com.hillel.task_27_springdata.exception.NoSuchProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long create(Product product) {
        final String SQL_INSERT = "INSERT INTO products(name, cost) VALUES(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(SQL_INSERT, new String[]{"id"});

                    preparedStatement.setString(1, product.getName());
                    preparedStatement.setBigDecimal(2, product.getCost());

                    return preparedStatement;
                }
                , keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean deleteById(Long id) {
        final String SQL_DELETE_BY_ID_CHILD = "DELETE FROM carts_products WHERE products_id = ?";
        jdbcTemplate.update(SQL_DELETE_BY_ID_CHILD, id);

        final String SQL_DELETE_BY_ID_PARENT = "DELETE FROM products WHERE id = ?";
        return jdbcTemplate.update(SQL_DELETE_BY_ID_PARENT, id) > 0;
    }

    @Override
    public Product getById(Long id) {
        String SQL_GET_BY_ID = "SELECT * FROM products WHERE id = ?";

        Product productFound = jdbcTemplate.query(SQL_GET_BY_ID, (resultSet) -> {
            Product product = null;

            if (resultSet.next()) {
                product = new Product(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("cost"));
            }

            return product;
        }, id);

        checkProduct(productFound, id);

        return productFound;
    }

    public List<Product> getAll() {
        String SQL_GET_ALL = "SELECT * FROM products";

        return jdbcTemplate.query(SQL_GET_ALL, (resultSet) -> {
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("cost"));

                products.add(product);
            }

            return products;
        });
    }

    private void checkProduct(Product product, Long id) {
        if (product == null) {
            throw new NoSuchProductException("Can't find product with id " + id);
        }
    }
}
