package com.spring.dallija.service;

import com.spring.dallija.controller.dto.ItemDto;
import com.spring.dallija.controller.dto.ItemDto.ItemResponse;
import com.spring.dallija.domain.category.Category;
import com.spring.dallija.domain.category.CategoryItem;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.exception.category.NotFoundCategoryException;
import com.spring.dallija.exception.item.ItemNotFoundException;
import com.spring.dallija.repository.category.CategoryRepository;
import com.spring.dallija.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.spring.dallija.controller.dto.ItemDto.*;
import static com.spring.dallija.controller.dto.ItemDto.UpdateItemsRequest;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Item saveItem(SaveItemsRequest saveItemsRequest) {
        Item item = saveItemsRequest.toEntity();
        if (saveItemsRequest.getCategoryName() != null) {
            Category category = categoryRepository.findByName(saveItemsRequest.getCategoryName())
                    .orElseThrow(NotFoundCategoryException::new);
            item.addCategoryItem(CategoryItem.createCategoryItem(category));
        }
        return itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(UpdateItemsRequest updateItemsRequest) {
        Item findItem = findById(updateItemsRequest.getId());

        findItem.changeItem(updateItemsRequest.getName(),
                updateItemsRequest.getPrice(),
                updateItemsRequest.getStockQuantity());

        return findItem;
    }

    @Transactional
    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
    }

    public Page<ItemResponse> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable).map(ItemResponse::new);
    }

}
