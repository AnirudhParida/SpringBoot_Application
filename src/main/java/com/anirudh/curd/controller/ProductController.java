package com.anirudh.curd.controller;

import com.anirudh.curd.model.Product;
import com.anirudh.curd.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/products/{prodId}")
    public Product getProduct(@PathVariable int prodId){
        return productService.getProduct(prodId);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product prod){
        return productService.addProduct(prod);
    }

    @PutMapping("/products/{prodId}")
    public Product updateProduct(@RequestBody Product prod, @PathVariable int prodId){
        return productService.updateProduct(prod, prodId );
    }

    @DeleteMapping("/products/{prodId}")
    public String deleteProduct(@PathVariable int prodId){
        productService.deleteProduct(prodId);
        return "Product" + prodId + "Deleted";
    }

}
