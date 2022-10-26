package com.spring.dallija.service;

import com.spring.dallija.controller.dto.ItemDto;
import com.spring.dallija.controller.dto.ItemDto.ItemResponse;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.exception.item.NotFoundItemException;
import com.spring.dallija.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item saveItem(Item item) {
        itemRepository.save(item);
        return item;
    }

    @Transactional
    public Item updateItem(ItemDto.UpdateItemsRequest updateItemsRequest) {
        Item findItem = findById(updateItemsRequest.getId());

        findItem.changeItem(updateItemsRequest.getName(),
                updateItemsRequest.getPrice(),
                updateItemsRequest.getStockQuantity());

        return findItem;
    }

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(NotFoundItemException::new);
    }

    public Page<ItemResponse> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable).map(i-> i.buildItemResponse());
    }

}
