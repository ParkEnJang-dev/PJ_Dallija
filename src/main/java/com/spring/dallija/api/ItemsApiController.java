package com.spring.dallija.api;

import com.spring.dallija.api.dto.ItemsDto;
import com.spring.dallija.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemsApiController {

    private final ItemService itemService;

    @PostMapping("/v1/items")
    public void saveItem(@RequestBody @Valid ItemsDto.SaveItemsRequest saveItemsRequest){
        itemService.saveItem(saveItemsRequest.toEntity());
    }

    @PatchMapping("/v1/items/edit")
    public void updateItem(@RequestBody @Valid ItemsDto.UpdateItemsRequest updateItemsRequest){
        itemService.updateItem(updateItemsRequest);
    }

}
