package com.reactive.app.springreactiveapp.service;

import com.reactive.app.springreactiveapp.entity.Cart;
import com.reactive.app.springreactiveapp.entity.Product;
import com.reactive.app.springreactiveapp.request.CartRequest;
import reactor.core.publisher.Mono;

public interface CartService {

    Mono<Cart> created(CartRequest request, String cartId);
}
