package com.james.ecommercestore.controller;

import com.james.ecommercestore.model.Product;
import com.james.ecommercestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //add product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }


    //update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()){
            Product existingProduct = product.get();
            existingProduct.setTitle(productDetails.getTitle());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setCost(productDetails.getCost());
            existingProduct.setQuantity(productDetails.getQuantity());
            existingProduct.setImageUrl(productDetails.getImageUrl());
            existingProduct.setCategory(productDetails.getCategory());
        } else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
