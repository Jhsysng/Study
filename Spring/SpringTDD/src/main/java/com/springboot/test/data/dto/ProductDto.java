package com.springboot.test.data.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {

    private String name;
    private int price;
    private int stock;
}
