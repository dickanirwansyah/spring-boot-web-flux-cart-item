package com.reactive.app.springreactiveapp.repository;

import com.reactive.app.springreactiveapp.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String>{
}
