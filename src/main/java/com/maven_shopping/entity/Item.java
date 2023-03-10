package com.maven_shopping.entity;

import com.maven_shopping.constant.ItemSellStatus;
import com.maven_shopping.dto.ItemFormDto;
import com.maven_shopping.exception.OutOfStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity{
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String itemName;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(nullable = false)
    private int stockNum;
//    @Lob
    @Column(nullable = false)
    private String itemDetail;
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    public void updateItem(ItemFormDto itemFormDto) {
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockNum = itemFormDto.getStockNum();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }
    public void removedStock(int stockNum) {
        int restStock = this.stockNum - stockNum;
        if(restStock<0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량: " + this.stockNum + ")");
        }
        this.stockNum = restStock;
    }
    public void addStock(int stockNum) {
        this.stockNum += stockNum;
    }
}
