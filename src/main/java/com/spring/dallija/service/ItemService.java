package com.spring.dallija.service;

import com.spring.dallija.domain.item.Book;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Long itemId, String name, int price, int stockQuantity){
        //아래 로직이 merge 랑 같음, 하지만 파마리터 값이 전부변경됨.
        //아래 처럼 해당 값만 변경할때는 아래 로직을 사용하자. merge는 사용하지 말자 .
        Item findItem = itemRepository.findOne(itemId);

        //의미있는 메서드를 써야돼.
        //findItem.change(price, name, stockQuantiry)
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        return findItem;
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
