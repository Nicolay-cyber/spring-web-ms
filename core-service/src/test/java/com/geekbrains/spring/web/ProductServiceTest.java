package com.geekbrains.spring.web;

import com.geekbrains.spring.web.core.dto.ProductDto;
import com.geekbrains.spring.web.core.entities.Product;
import com.geekbrains.spring.web.core.repositories.ProductsRepository;
import com.geekbrains.spring.web.core.repositories.specifications.ProductsSpecifications;
import com.geekbrains.spring.web.core.services.ProductsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {ProductsService.class})
public class ProductServiceTest {
    @Autowired
    private ProductsService productsService;
    @MockBean
    private ProductsRepository productsRepository;

    @Test
    public void findByIdTest() {
        Product orange = new Product(1L, "Orange", 200);
        Mockito.doReturn(Optional.of(orange)).when(productsRepository).findById(1L);
        Product product = productsService.findById(1L).get();
        Assertions.assertNotNull(product);
        Assertions.assertEquals(orange, product);
        Mockito.verify(productsRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    }

    @Test
    public void updateTest() {
        Product orange = new Product(1L, "Orange", 200);
        Product exceptedOrange = new Product(1L, "Orange", 300);

        ProductDto newOrange = new ProductDto(1L, "Orange", 300);
        Mockito.doReturn(Optional.of(orange)).when(productsRepository).findById(1L);
        Product updatedOrange = productsService.update(newOrange);
        Assertions.assertNotNull(updatedOrange);
        Assertions.assertEquals(exceptedOrange, updatedOrange);
        Mockito.verify(productsRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    }

    @Test
    public void findAllTest() {
        Product orange = new Product(1L, "Orange", 200);
        Product apple = new Product(2L, "Apple", 300);
        Product nut = new Product(3L, "Nut", 400);
        ArrayList<Product> products = new ArrayList<>();
        products.add(orange);
        products.add(apple);
        products.add(nut);
        Page<Product> allProducts = new PageImpl(List.of(orange,apple,nut));
        Specification<Product> spec = Specification.where(null);
        Pageable pageable =  PageRequest.of(0, 8);

        Mockito.doReturn(allProducts).when(productsRepository).findAll(spec, pageable);
        Page<Product> obtainedProductList = productsService.findAll(null, null, null, 1);
        Assertions.assertEquals(allProducts, obtainedProductList);
    }
}
