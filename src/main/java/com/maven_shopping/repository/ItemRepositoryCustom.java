package com.maven_shopping.repository;

import com.maven_shopping.dto.ItemSearchDto;
import com.maven_shopping.dto.MainItemDto;
import com.maven_shopping.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
