package com.vkuppan.cp.product.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vkuppan.cp.product.dto.ProductPurchaseRequest;
import com.vkuppan.cp.product.dto.ProductPurchaseResponse;
import com.vkuppan.cp.product.dto.ProductRequest;
import com.vkuppan.cp.product.dto.ProductResponse;
import com.vkuppan.cp.product.exception.ProductPurchaseException;
import com.vkuppan.cp.product.repository.ProductRepository;
import com.vkuppan.cp.product.service.ProductService;
import com.vkuppan.cp.product.util.ProductUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService
{
    private final ProductRepository productRepository;

    private ProductUtil productUtil;

    public ProductServiceImpl(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest)
    {
        var product = productUtil.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    @Override
    public ProductResponse findById(Integer id)
    {
        return productRepository.findById(id).map(productUtil::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));
    }

    @Override
    public List<ProductResponse> findAll()
    {
        return productRepository.findAll().stream().map(productUtil::toProductResponse).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public ArrayList<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request)
    {
        var productIds = request.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size())
        {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        var sortedRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++)
        {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity())
            {
                throw new ProductPurchaseException(
                        "Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productUtil.toproductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

}
