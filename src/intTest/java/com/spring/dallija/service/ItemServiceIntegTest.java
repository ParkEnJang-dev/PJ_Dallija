package com.spring.dallija.service;

import com.spring.dallija.api.dto.ItemsDto;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.domain.item.Meat;
import com.spring.dallija.repository.ItemRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
        Item item = new Meat("소고기 볶음", 10000, 100, "횡성", LocalDateTime.now(), "BEEF");
        itemService.saveItem(item);

        //when
        Item findItem = itemService.findOne(item.getId());

        //then
        assertThat(findItem).isEqualTo(item);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 상품_수정() throws Exception {
        //given
        Item item = new Meat("소고기 볶음", 10000, 100, "횡성", LocalDateTime.now(), "BEEF");
        itemService.saveItem(item);

        ItemsDto.UpdateItemsRequest updateItemsRequest = new ItemsDto.UpdateItemsRequest(item.getId(), "소 구이",5000,50);

        //when
        Item result = itemService.updateItem(updateItemsRequest);

        //then
        assertThat(result.getName()).isEqualTo(updateItemsRequest.getName());

     }



}
