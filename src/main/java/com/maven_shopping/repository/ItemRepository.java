package com.maven_shopping.repository;

import com.maven_shopping.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    List<Item> findByItemName(String itemName);

    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

//    //JPQL로 작성한 쿼리문을 insert
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
//    //파라미터로 넘어온 값을 JPQL에 들어갈 변수로 지정
//    //like %% 사이에 :ItemDetail 값이 들어가도록 작성
    List<Item> findByItemDetail(@Param("itemDetail")String itemDetail);

    @Query(value = "select * from item i where i.item_Detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}
