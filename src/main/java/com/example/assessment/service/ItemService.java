package com.example.assessment.service;

import com.example.assessment.dto.ItemNewDTO;
import com.example.assessment.dto.ItemResponseDTO;
import com.example.assessment.entity.ItemEntity;
import com.example.assessment.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public ItemEntity saveItem(ItemNewDTO itemNewDTO) {
        return itemRepository.save(ItemEntity.builder()
                .name(itemNewDTO.getName())
                .description(itemNewDTO.getDescription()).build());
    }

    @Transactional(readOnly = true)
    public List<ItemResponseDTO> getItemByPagination(int page, int size) {
        Page<ItemEntity> itemPage = itemRepository.findAll(PageRequest.of(page, size));
        return itemPage.getContent().stream()
                .map(this::convertToItemDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ItemEntity updateItem(Long id, ItemNewDTO itemNewDTO) {
        if (itemRepository.existsById(id)) {
            return itemRepository.save(ItemEntity.builder()
                    .id(id)
                    .name(itemNewDTO.getName())
                    .description(itemNewDTO.getDescription()).build());
        } else {
            throw new EntityNotFoundException("Item not found");
        }
    }

    private ItemResponseDTO convertToItemDTO(ItemEntity itemEntity) {
        return new ItemResponseDTO(itemEntity.getId(), itemEntity.getName(), itemEntity.getDescription());
    }
}
