package com.example.assessment.controller;

import com.example.assessment.dto.ItemDTO;
import com.example.assessment.service.ExternalApiService;
import com.example.assessment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/common")
public class CommonController {

    @Autowired
    private ExternalApiService externalApiService;
    @Autowired
    private ItemService itemService;


    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> getItemByPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        return ResponseEntity.ok(itemService.getItemByPagination(page, size));
    }

    @GetMapping("/call-external")
    public ResponseEntity<?> callExternalApi() {
        return ResponseEntity.ok(externalApiService.externalUrlCall());
    }
}
