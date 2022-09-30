package com.spring.dallija.service;

import com.spring.dallija.domain.item.Items;
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
    public void saveItem(Items item) {
        itemRepository.save(item);
    }

    public Items findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public List<Items> findItems() {
        return itemRepository.findAll();
    }

}
