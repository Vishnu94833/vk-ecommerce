package com.vkuppan.cp.product.util;

import com.vkuppan.cp.product.dto.ProductPurchaseResponse;
import com.vkuppan.cp.product.dto.ProductRequest;
import com.vkuppan.cp.product.dto.ProductResponse;
import com.vkuppan.cp.product.entity.Category;
import com.vkuppan.cp.product.entity.Product;

public class ProductUtil
{
    public Product toProduct(ProductRequest productRequest)
    {
        return Product.builder().id(productRequest.id()).name(productRequest.name()).price(productRequest.price())
                .description(productRequest.description()).availableQuantity(productRequest.availableQuantity())
                .category(Category.builder().id(productRequest.categoryId()).build()).build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }

}
