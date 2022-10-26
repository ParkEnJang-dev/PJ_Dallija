package com.spring.dallija.api;

import com.spring.dallija.api.dto.ItemDto;
import com.spring.dallija.common.anotation.LoginCheck;
import com.spring.dallija.domain.user.UserRole;
import com.spring.dallija.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemsApiController {

    private final ItemService itemService;

    @LoginCheck(userRole = UserRole.ADMIN)
    @PostMapping("/item")
    public void saveItem(@RequestBody @Valid ItemDto.SaveItemsRequest saveItemsRequest){
        itemService.saveItem(saveItemsRequest.toEntity());
    }

    @LoginCheck(userRole = UserRole.ADMIN)
    @PatchMapping("/item/edit")
    public void updateItem(@RequestBody @Valid ItemDto.UpdateItemsRequest updateItemsRequest){
        itemService.updateItem(updateItemsRequest);
    }

}
