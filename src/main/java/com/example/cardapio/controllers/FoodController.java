package com.example.cardapio.controllers;

import com.example.cardapio.DTO.FoodRequestDTO;
import com.example.cardapio.DTO.FoodResponseDTO;
import com.example.cardapio.entity.Food;
import com.example.cardapio.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.cardapio.repository.FoodRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository foodRepository;
    private FoodService foodService;

    @PostMapping("/new")
    public ResponseEntity<Food> newFood(@RequestBody final FoodRequestDTO foodRequestDTO){
        Food food = new Food(foodRequestDTO);
        foodRepository.save(food);

        return ResponseEntity.ok(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodById(@PathVariable final Long id, @RequestBody final FoodRequestDTO foodRequestDTO){
        Food food = foodService.updateFood(id, foodRequestDTO);
        return ResponseEntity.ok(food);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodById(@PathVariable final Long id){
        Food food = foodRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found with id: " + id)
        );
        foodRepository.delete(food);
    }
    @GetMapping("/list")
    public List<FoodResponseDTO> getAllFoods(){
        List<FoodResponseDTO> foodList = foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();

        return foodList;
    }

    @GetMapping("/name")
    public FoodResponseDTO getFoodByName(@RequestParam final String name){
        Food food = foodRepository.findByName(name.toUpperCase().trim()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found with name: " + name)
        );
        FoodResponseDTO foodResponseDTO = new FoodResponseDTO(food);

        return foodResponseDTO;
    }
}
