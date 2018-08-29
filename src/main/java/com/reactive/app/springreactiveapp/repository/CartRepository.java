package com.reactive.app.springreactiveapp.repository;

import com.reactive.app.springreactiveapp.entity.Cart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CartRepository extends ReactiveMongoRepository<Cart, String> {
}
