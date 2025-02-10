package com.anirudh.curd.services;

import com.anirudh.curd.model.Product;
import com.anirudh.curd.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getProducts(){
        return productRepo.findAll();
    }

    public Product getProduct(int prodId) {
        return productRepo.findById(prodId).orElseThrow(()-> new RuntimeException("Product not found with id: " + prodId));
    }

    public Product addProduct(Product prod){
        productRepo.save(prod);
        return prod;
    }

    public Product updateProduct(Product prod, int prodId) {
        return productRepo.findById(prodId)
                .map(existingProduct -> {
                    existingProduct.setName(prod.getName());
                    existingProduct.setPrice(prod.getPrice());
                    existingProduct.setQuantity(prod.getQuantity());
                    return productRepo.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + prod.getProdId()));
    }

    public void deleteProduct(int prodId) {
        productRepo.deleteById(prodId);
    }
}
