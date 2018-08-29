package com.reactive.app.springreactiveapp.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    private String productId;

    private String name;

    private Integer stock;

    private Long price;
}
