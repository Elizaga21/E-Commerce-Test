package com.proyecto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart implements Serializable {
    
    private String id;
    private List<Product> products;

    public Cart(String id) {
        this.id = id;
        this.products = new ArrayList<>();
    }

}
