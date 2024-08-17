package com.vkuppan.cp.product.service;

import java.util.ArrayList;
import java.util.List;

import com.vkuppan.cp.product.dto.ProductPurchaseRequest;
import com.vkuppan.cp.product.dto.ProductRequest;
import com.vkuppan.cp.product.dto.ProductResponse;

public interface ProductService
{
    Integer createProduct(ProductRequest productRequest);

    ArrayList<Object> purchaseProducts(List<ProductPurchaseRequest> productRequest) throws Exception;

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();
}
