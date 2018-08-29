package com.reactive.app.springreactiveapp.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CartRequest {

    private String cartId;

    @NotBlank
    private String productId;

    @NotNull
    private Integer quantity;
}
