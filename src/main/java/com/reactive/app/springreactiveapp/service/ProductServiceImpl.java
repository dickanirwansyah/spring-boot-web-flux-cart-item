package com.reactive.app.springreactiveapp.service;

import com.reactive.app.springreactiveapp.entity.Product;
import com.reactive.app.springreactiveapp.repository.ProductRepository;
import com.reactive.app.springreactiveapp.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductServiceImpl implements ProductService{

    @Autowired private ProductRepository productRepository;

    @Override
    public Mono<Product> created(ProductRequest productRequest) {

        Product product = Product
                .builder()
                .name(productRequest.getName())
                .stock(productRequest.getStock())
                .price(productRequest.getPrice())
                .build();

        return productRepository.save(product);
    }

    @Override
    public Mono<Product> updated(ProductRequest productRequest, String productId) {

        Product product = Product
                .builder()
                .id(productId)
                .name(productRequest.getName())
                .stock(productRequest.getStock())
                .price(productRequest.getPrice())
                .build();

        return productRepository.save(product);
    }
}
