package com.vkuppan.cp.product.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vkuppan.cp.product.dto.ProductPurchaseRequest;
import com.vkuppan.cp.product.dto.ProductRequest;
import com.vkuppan.cp.product.dto.ProductResponse;
import com.vkuppan.cp.product.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController
{
    private  final ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Integer> addProduct(@RequestBody @Valid ProductRequest productRequest)
    {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<ArrayList<Object>> purchaseProducts(@RequestBody @Valid List<ProductPurchaseRequest> productRequest)
            throws Exception
    {
        return ResponseEntity.ok(productService.purchaseProducts(productRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") Integer productId)
    {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll()
    {
        return ResponseEntity.ok(productService.findAll());
    }
}
