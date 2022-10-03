package com.spring.dallija.service;

import com.spring.dallija.api.dto.ItemsDto;
import com.spring.dallija.domain.item.Items;
import com.spring.dallija.domain.item.Meat;
import com.spring.dallija.domain.item.MeatType;
import com.spring.dallija.repository.ItemRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepositoryImpl itemRepository;

    @Transactional
    public Items saveItem(Items item) {
        itemRepository.save(item);
        return item;
    }

    @Transactional
    public Items updateItem(ItemsDto.UpdateItemsRequest updateItemsRequest) {
        Items findItem = findOne(updateItemsRequest.getId());

        findItem.changeItem(updateItemsRequest.getName(),
                updateItemsRequest.getPrice(),
                updateItemsRequest.getStockQuantity());

        return findItem;
    }

    public Items findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public List<Items> findItems() {
        return itemRepository.findAll();
    }

}
