package com.reactive.app.springreactiveapp.service;

import com.reactive.app.springreactiveapp.entity.Cart;
import com.reactive.app.springreactiveapp.entity.CartItem;
import com.reactive.app.springreactiveapp.entity.Product;
import com.reactive.app.springreactiveapp.repository.CartRepository;
import com.reactive.app.springreactiveapp.repository.ProductRepository;
import com.reactive.app.springreactiveapp.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CartServiceImpl implements CartService {

    @Autowired private CartRepository cartRepository;

    @Autowired private ProductRepository productRepository;

    @Override
    public Mono<Cart> created(CartRequest request, String cartId) {
        return Mono.zip(
                objects -> autoMaticUpdateAndAdd((Cart) objects[0], (Product)objects[1], request.getQuantity()),
                cartRepository.findById(cartId),
                productRepository.findById(request.getProductId())
        ).flatMap(cart -> cartRepository.save(cart));
    }

    /** automatic update update and add method**/
    private Cart autoMaticUpdateAndAdd(Cart cart, Product product, Integer quantity){
        if (isContaitItem(cart, product)){
            incrementQuantityItem(cart, product, quantity);
        }else{
            addToCart(cart, product, quantity);
        }
        return cart;
    }

    /** isContaint item **/
    private boolean isContaitItem(Cart cart, Product product){
        return cart.getItems()
                .stream()
                .anyMatch(cartItem -> cartItem.getId()
                        .equals(product.getId()));
    }

    /** automatic increment item **/
    private void incrementQuantityItem(Cart cart, Product product, Integer quantity){
        cart.getItems().stream()
                .filter(cartItem -> cartItem.getId().equals(product.getId()))
                .forEach(cartItem -> cartItem.setQuantity(cartItem.getQuantity() + quantity));
    }

    /** add cart item ke table **/
    private void addToCart(Cart cart, Product product, Integer quantity){
        CartItem cartItem = CartItem
                .builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(quantity)
                .build();
        cart.getItems().add(cartItem);
    }
}
