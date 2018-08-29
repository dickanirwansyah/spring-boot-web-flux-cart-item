package com.reactive.app.springreactiveapp.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "cart")
public class Cart {

    @Id
    private String id;

    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems(){
        if (items == null || items.isEmpty()){
            items = new ArrayList<>();
        }
        return items;
    }

    /**
    public Cart(){}

    public Cart(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setItems(List<CartItem> items){
        this.items = items;
    }
     **/
}
