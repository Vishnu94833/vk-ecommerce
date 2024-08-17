package com.vkuppan.cp.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vkuppan.cp.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
    List<Product> findAllByIdInOrderById(List<Integer> ids);
}
