package com.reactive.app.springreactiveapp.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Document(collection = "product")
public class Product {

    @Id
    private String id;

    @NotBlank
    private String name;

    @Max(100)
    @Min(1)
    @NotNull
    private Integer stock;

    @NotNull
    private Long price;


}
