package com.spring.dallija.service;

import com.spring.dallija.controller.dto.ItemDto;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemServiceIntegTest {

    @Autowired
    ItemRepository itemRepository;

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

     @Test
     @Transactional
     public void 상품_조회() throws Exception {
         //given
         //List<Item> items = itemService.findItems();
         //log.debug("size {}", items.size());

         PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));

         Page<Item> page = itemRepository.findAll(pageRequest);

         List<Item> content = page.getContent();
         long totalElements = page.getTotalElements();
         for (Item item : content) {
             System.out.println("item = " + item.getId());
         }

         System.out.println("totalElements = " + totalElements);
         System.out.println("page.isFirst = " + page.isFirst());

         //when

         //then
         assertThat(content.size()).isEqualTo(10);
         assertThat(page.getTotalElements()).isEqualTo(104);
         assertThat(page.getNumber()).isEqualTo(0);
         assertThat(page.isFirst()).isEqualTo(true);
         assertThat(page.hasNext()).isEqualTo(true);

         System.out.println("page = " + page.getTotalPages());

      }





}
