package org.ericwubbo.atquerydemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select e from #{#entityName} e where e.deleted=false")
    List<Item> findAllPurchasable();
}
