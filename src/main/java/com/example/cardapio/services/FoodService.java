package com.example.cardapio.services;

import com.example.cardapio.DTO.FoodRequestDTO;
import com.example.cardapio.entity.Food;
import com.example.cardapio.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public Food updateFood(Long id, final FoodRequestDTO foodRequestDTO){

        Food food = foodRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found with id: " + id)
        );

        if(foodRequestDTO.name() != null){
            if(!foodRequestDTO.name().isEmpty()){
                food.setName(foodRequestDTO.name());
            }
        }
        if(foodRequestDTO.image() != null){
            if(!foodRequestDTO.image().isEmpty()){
                food.setImage(foodRequestDTO.image());
            }
        }
        if(foodRequestDTO.price() != null){
        }

        foodRepository.save(food);
        return food;
    }
}
