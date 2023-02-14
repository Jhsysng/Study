package com.springboot.valid_exception.data.dto;

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
