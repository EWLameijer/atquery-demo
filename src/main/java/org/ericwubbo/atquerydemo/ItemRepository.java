package org.ericwubbo.atquerydemo;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select e from #{#entityName} e where e.deleted=false")
    List<Item> findAllPurchasable();

    @Override
    @Modifying
    @Query("update #{#entityName} set deleted=true where id=?1")
    void deleteById(@Nonnull Long id);
}
