package com.kukucorn.jejukindstore.domain.storemenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreMenuRepository extends JpaRepository<StoreMenu, Integer> {

    @Query("SELECT m FROM store_menu m WHERE m.store.id = ?1")
    List<StoreMenu> findAllByStoreId(Integer store_id);
}
