package com.example.cardapio.DTO;

import com.example.cardapio.entity.Food;

import java.math.BigDecimal;

public record FoodRequestDTO(String name, String image, BigDecimal price) {
}