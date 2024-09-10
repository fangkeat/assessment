package com.example.assessment.service;

import com.example.assessment.dto.ItemDTO;
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
    public ItemEntity saveItem(ItemEntity item) {
        return itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<ItemDTO> getItemByPagination(int page, int size) {
        Page<ItemEntity> itemPage = itemRepository.findAll(PageRequest.of(page, size));
        return itemPage.getContent().stream()
                .map(this::convertToItemDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ItemEntity updateItem(Long id, ItemEntity item) {
        if (itemRepository.existsById(id)) {
            item.setId(id);
            return itemRepository.save(item);
        } else {
            throw new EntityNotFoundException("Item not found");
        }
    }

    private ItemDTO convertToItemDTO(ItemEntity itemEntity) {
        return new ItemDTO(itemEntity.getId(), itemEntity.getName(), itemEntity.getDescription());
    }
}
