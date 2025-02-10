package com.anirudh.curd.services;

import com.anirudh.curd.model.Product;
import com.anirudh.curd.repo.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepo productRepo;

    private Product product;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        product = new Product(1, "Product1", 100, 10);
    }

    @Test
    void testGetProducts(){
        List<Product> products = Arrays.asList(product);
        when(productRepo.findAll()).thenReturn(products);

        List<Product> result = productService.getProducts();
        assertEquals(1, result.size());
        assertEquals("Product1", result.getFirst().getName());
    }

    @Test
    void testGetProduct(){
        when(productRepo.findById(1)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(1);

        assertEquals("Product1", result.getName());
    }

    @Test
    void testGetProductNotFound(){
        when(productRepo.findById(2)).thenThrow(new RuntimeException("Product not found with id: 2"));
        assertEquals(0, productService.getProducts().size());
    }

    @Test
    void testAddProducts(){
        Product newProduct = new Product(2,"Product2",101,3);
        when(productRepo.save(newProduct)).thenReturn(newProduct);

        Product result = productService.addProduct(newProduct);

        assertEquals("Product2", result.getName());
    }

    @Test
    void testUpdateProduct(){
        int prodId = 1;
        Product updatedProduct = new Product(prodId,"Product1",200,20);
        when(productRepo.findById(prodId)).thenReturn(Optional.of(product));
        when(productRepo.save(product)).thenReturn(updatedProduct);

        Product result = productService.updateProduct(updatedProduct, prodId);

        assertNotNull(result);
        assertEquals(200, result.getQuantity());

    }

    @Test
    void testDeleteProduct(){
        int prodId = 1;
        when(productRepo.findById(prodId)).thenReturn(Optional.of(product));
        productService.deleteProduct(prodId);
        assertEquals(0, productService.getProducts().size());
    }

}
