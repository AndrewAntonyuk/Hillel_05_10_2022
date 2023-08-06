package com.hillel.online_shop.controller;

import com.hillel.online_shop.dto.cart.CartDTO;
import com.hillel.online_shop.dto.order.OrderDTO;
import com.hillel.online_shop.dto.product.ProductDTO;
import com.hillel.online_shop.dto.user.UserRequestDTO;
import com.hillel.online_shop.dto.user.UserResponseDTO;
import com.hillel.online_shop.entity.Product;
import com.hillel.online_shop.service.CartService;
import com.hillel.online_shop.service.OrderService;
import com.hillel.online_shop.service.ProductService;
import com.hillel.online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/customer")
public class CustomerController {
    private final CartService cartService;
    private final UserService<UserRequestDTO, UserResponseDTO> userService;
    private final ProductService productService;
    private final OrderService orderService;

    @PostMapping("/add-to-cart")
    public void addToCart(@RequestBody ProductDTO productDTO) {
        cartService.add(getCartId(), productDTO);
    }

    @DeleteMapping("/remove-from-cart/{productId}")
    public void removeFromCart(@PathVariable Long productId) {
        cartService.remove(getCartId(), productId);
    }

    @PutMapping("/reduce-product-from-cart")
    public void reduceProductFromCart(@RequestBody ProductDTO productDTO) {
        cartService.reduceQuantity(getCartId(), productDTO);
    }

    @GetMapping("/get-cart")
    public CartDTO getCart() {
        return cartService.findById(getCartId());
    }

    @DeleteMapping("/clear-cart")
    public void clearCart() {
        cartService.clear(getCartId());
    }

    @PostMapping("/make-order")
    public ResponseEntity<String> makeOrder() {
        UserResponseDTO userResponseDTO = userService.findById(getCurrentUserId());
        CartDTO cartDTO = cartService.findById(getCartId());

        if (userResponseDTO.getBalance().compareTo(cartDTO.getCost()) < 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Insufficient funds in user's balance");
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCost(cartDTO.getCost());
        orderDTO.setDate(LocalDate.now());
        orderService.create(getCurrentUserId(), orderDTO);

        return ResponseEntity.ok("Order successfully created");
    }

    @GetMapping("/get-all-orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.findAllByUserId(getCurrentUserId());
    }

    @GetMapping("/get-account-info")
    public UserResponseDTO getAccountInfo() {
        return userService.findByLogin(getCurrentUserLogin());
    }

    @PutMapping("/update-account-info")
    public void updateAccountInfo(@RequestBody UserRequestDTO userRequestDTO) {
        if(userRequestDTO.getRole() != null) {
            throw new IllegalArgumentException("you can not change your role");
        }

        userRequestDTO.setId(getCurrentUserId());
        userService.update(userRequestDTO);
    }

    @PutMapping("/set-balance")
    public void setBalance(@RequestParam BigDecimal balance) {
        if(balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("balance must be positive");
        }

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setId(getCurrentUserId());
        userRequestDTO.setBalance(balance);
        userService.update(userRequestDTO);
    }

    @GetMapping("/show-products")
    public List<ProductDTO> getAll() {
        return productService.findAll();
    }

    private Long getCurrentUserId() {
        return userService.findByLogin(getCurrentUserLogin()).getId();
    }

    private String getCurrentUserLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    private Long getCartId() {
        return cartService.findByUserId(getCurrentUserId()).getId();
    }
}
