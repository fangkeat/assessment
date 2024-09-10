package com.example.assessment.controller;

import com.example.assessment.dto.ItemNewDTO;
import com.example.assessment.dto.ItemResponseDTO;
import com.example.assessment.entity.ItemEntity;
import com.example.assessment.service.ExternalApiService;
import com.example.assessment.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/common")
public class CommonController {

    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public ResponseEntity<List<ItemResponseDTO>> getItemByPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        return ResponseEntity.ok(itemService.getItemByPagination(page, size));
    }

    // Add a new item
    @PostMapping("/item")
    public ResponseEntity<ItemEntity> addItem(@Valid @RequestBody ItemNewDTO itemNewDTO) {
        ItemEntity createdItem = itemService.saveItem(itemNewDTO);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    // Update an existing item
    @PutMapping("/item/{id}")
    public ResponseEntity<ItemEntity> updateItem(@PathVariable Long id, @Valid @RequestBody ItemNewDTO itemNewDTO) {
        ItemEntity updatedItem = itemService.updateItem(id, itemNewDTO);
        return ResponseEntity.ok(updatedItem);
    }

    // External API
    @GetMapping("/call-external")
    public ResponseEntity<?> callExternalApi() {
        return ResponseEntity.ok(externalApiService.externalUrlCall());
    }
}
