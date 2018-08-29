package com.reactive.app.springreactiveapp.controller;

import com.reactive.app.springreactiveapp.entity.Product;
import com.reactive.app.springreactiveapp.repository.ProductRepository;
import com.reactive.app.springreactiveapp.request.ProductRequest;
import com.reactive.app.springreactiveapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    /** repository **/
    @Autowired
    private ProductRepository productRepository;

    /** service **/
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/saved")
    public Mono<ResponseEntity<Product>> saved(@RequestBody ProductRequest request){

        return productService.created(request)
                .map(result -> ResponseEntity.ok(result))
                .defaultIfEmpty(ResponseEntity.badRequest()
                .build());
    }

    @PutMapping(value = "/updated/{productId}")
    public Mono<ResponseEntity<Product>> updated(@PathVariable("productId")String productId,
                                                 @RequestBody ProductRequest productRequest){

        return productService.updated(productRequest, productId)
                .map(result -> ResponseEntity.ok(result))
                .defaultIfEmpty(ResponseEntity.badRequest()
                .build());
    }

    @GetMapping
    public Flux<Product> getProducts(){
        return productRepository.findAll();
    }

    @PostMapping
    public Mono<ResponseEntity<Product>> created(@RequestBody Product request){
          return productRepository.save(request)
                  .map(result -> ResponseEntity.ok(request))
                  .defaultIfEmpty(ResponseEntity.badRequest()
                          .build());
    }

    @GetMapping(value = "/{productId}")
    public Mono<ResponseEntity<Product>> getId(@PathVariable("productId")String productId){
        return productRepository.findById(productId)
                .map(result -> ResponseEntity.ok(result))
                .defaultIfEmpty(ResponseEntity.notFound()
                        .build());
    }

    @PutMapping(value = "/{productId}")
    public Mono<ResponseEntity<Product>> update(@PathVariable("productId")String productId,
                                                @RequestBody Product product){
        return productRepository.findById(productId)
                .flatMap(existsProduct -> {
                    existsProduct.setName(product.getName());
                    existsProduct.setPrice(product.getPrice());
                    existsProduct.setStock(product.getStock());
                    return productRepository.save(existsProduct);
                })
                .map(result ->
                        new ResponseEntity<Product>(result, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<Product>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{productId}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("productId")String productId){

        return productRepository.findById(productId)
                .flatMap(existsProduct ->
                    productRepository.delete(existsProduct)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}
