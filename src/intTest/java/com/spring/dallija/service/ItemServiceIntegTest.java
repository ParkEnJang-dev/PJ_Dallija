package com.spring.dallija.service;

import com.spring.dallija.api.dto.ItemDto;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.repository.ItemRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemServiceIntegTest {

    @Autowired
    ItemRepositoryImpl itemRepository;

    @Autowired
    ItemService itemService;

    @Test
    @Transactional
    public void 상품_저장() throws Exception {
        //given
        Item item = new Item("소고기 볶음", 10000, 100, "횡성");
        itemService.saveItem(item);

        //when
        Item findItem = itemService.findById(item.getId());

        //then
        assertThat(findItem).isEqualTo(item);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 상품_수정() throws Exception {
        //given
        Item item = new Item("소고기 볶음", 10000, 100, "횡성");
        itemService.saveItem(item);

        ItemDto.UpdateItemsRequest updateItemsRequest = new ItemDto.UpdateItemsRequest(item.getId(), "소 구이",5000,50);

        //when
        Item result = itemService.updateItem(updateItemsRequest);

        //then
        assertThat(result.getName()).isEqualTo(updateItemsRequest.getName());

     }



}
