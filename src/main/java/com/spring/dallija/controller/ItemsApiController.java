package com.spring.dallija.controller;

import com.spring.dallija.common.anotation.LoginCheck;
import com.spring.dallija.domain.user.UserRole;
import com.spring.dallija.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.spring.dallija.controller.dto.ItemDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemsApiController {

    private final ItemService itemService;

    @LoginCheck(userRole = UserRole.ADMIN)
    @PostMapping
    public void saveItem(@RequestBody @Valid SaveItemsRequest saveItemsRequest){
        itemService.saveItem(saveItemsRequest);
    }

    @LoginCheck(userRole = UserRole.ADMIN)
    @PatchMapping("/edit")
    public void updateItem(@RequestBody @Valid UpdateItemsRequest updateItemsRequest){
        itemService.updateItem(updateItemsRequest);
    }

    @GetMapping("/{id}")
    public ItemResponse findItem(@PathVariable Long id){
        return new ItemResponse(itemService.findById(id));
    }

    @GetMapping
    public Page<ItemResponse> findAll(Pageable pageable){
        return itemService.findAll(pageable);
    }

}
