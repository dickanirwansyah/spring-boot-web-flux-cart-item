package com.reactive.app.springreactiveapp.service;

import com.reactive.app.springreactiveapp.entity.Product;
import com.reactive.app.springreactiveapp.request.ProductRequest;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> created(ProductRequest productRequest);
    Mono<Product> updated(ProductRequest productRequest, String productId);
}
