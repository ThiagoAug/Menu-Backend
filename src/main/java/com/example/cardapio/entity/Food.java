package com.example.cardapio.entity;

import com.example.cardapio.DTO.FoodRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "foods")
@Entity(name = "foods")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "IMAGE")
    private String image;


    @Column(name = "PRICE")
    private BigDecimal price;

    public Food(FoodRequestDTO foodRequestDTO){
        this.name = foodRequestDTO.name();
        this.image = foodRequestDTO.image();
        this.price = foodRequestDTO.price();
    }
}
