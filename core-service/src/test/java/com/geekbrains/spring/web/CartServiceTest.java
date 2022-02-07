package com.geekbrains.spring.web;

import com.geekbrains.spring.web.core.entities.Product;
import com.geekbrains.spring.web.core.services.CartService;
import com.geekbrains.spring.web.core.services.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest(classes = CartService.class)
public class CartServiceTest {
    @MockBean
    private ProductsService productsService;
    @MockBean
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private CartService cartService;

    @BeforeEach
    public void initCart() {
        //Выдает NPE в этом месте когда доходит до
        // redisTemplate.opsForValue().set(cartKey, new Cart());
        cartService.clearCart("test_cart");
    }

    @Test
    public void addToCartTest() {
        Product orange = new Product(1L,"Orange", 200);
        Product apple = new Product(2L,"Apple", 300);

    }
}
