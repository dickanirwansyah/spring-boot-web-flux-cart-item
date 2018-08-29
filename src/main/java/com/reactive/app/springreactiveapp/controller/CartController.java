package com.reactive.app.springreactiveapp.controller;

import com.reactive.app.springreactiveapp.entity.Cart;
import com.reactive.app.springreactiveapp.repository.CartRepository;
import com.reactive.app.springreactiveapp.request.CartRequest;
import com.reactive.app.springreactiveapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @PostMapping(value = "/{cartId}")
    public Mono<ResponseEntity<Cart>> create(@PathVariable("cartId")String cartId){

        Cart cart = Cart
                .builder()
                .id(cartId)
                .build();

        return cartRepository.save(cart)
                .map(result -> ResponseEntity.ok(result))
                .defaultIfEmpty(ResponseEntity
                        .badRequest()
                        .build());
    }

    @PostMapping(value = "/{cartId}/add-item")
    public Mono<ResponseEntity<Cart>> addToCart(@PathVariable("cartId")String cartId,
                                                @RequestBody CartRequest cartRequest){

        return cartService.created(cartRequest, cartId)
                .map(result -> ResponseEntity.ok(result))
                .defaultIfEmpty(ResponseEntity.notFound()
                        .build());
    }
}
