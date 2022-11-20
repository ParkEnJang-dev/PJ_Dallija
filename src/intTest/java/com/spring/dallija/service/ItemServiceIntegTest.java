package com.spring.dallija.service;

import com.spring.dallija.controller.dto.ItemDto;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.repository.item.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.spring.dallija.controller.dto.ItemDto.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("dev1")
public class ItemServiceIntegTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @Test
    @Transactional
    public void 상품_저장() throws Exception {
        //given
        Item item = new Item("마라 볶음", 10000, 100, "횡성");
        SaveItemsRequest saveItemsRequest = new SaveItemsRequest(item);
        saveItemsRequest.addCategoryName("MACHINE");

        //when
        Item result = itemService.saveItem(saveItemsRequest);

        //then
        assertThat(result.getName()).isEqualTo("마라 볶음");
    }

    @Test
    @Transactional
    public void 상품_수정() throws Exception {
        //given
        Item item = new Item("소고기 볶음", 10000, 100, "횡성");
        SaveItemsRequest saveItemsRequest = new SaveItemsRequest(item);

        Item saveItem = itemService.saveItem(saveItemsRequest);
        UpdateItemsRequest updateItemsRequest = new UpdateItemsRequest(saveItem.getId(), "소 구이", 5000, 50);

        //when
        Item result = itemService.updateItem(updateItemsRequest);

        //then
        assertThat(result.getName()).isEqualTo(updateItemsRequest.getName());

    }

    @Test
    @Transactional
    public void 상품_조회() throws Exception {
        //given
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<Item> page = itemRepository.findAll(pageRequest);

        //when
        List<Item> content = page.getContent();
        long totalElements = page.getTotalElements();
        for (Item item : content) {
            System.out.println("item = " + item.getId());
        }

        //then
        assertThat(content.size()).isEqualTo(10);
        assertThat(page.getTotalElements()).isEqualTo(104);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.isFirst()).isEqualTo(true);
        assertThat(page.hasNext()).isEqualTo(true);

    }

}