package org.ericwubbo.atquerydemo;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Override
    @Nonnull
    default List<Item> findAll() {
        return findByDeletedIsFalse();
    }

    @Override
    default void deleteById(@Nonnull Long id) {
        Optional<Item> possibleItem = findById(id);
        if (possibleItem.isEmpty()) throw new IllegalArgumentException("Item with id " + id + " does not exist!");
        Item item = possibleItem.get();
        item.setDeleted(true);
        save(item);
    }

    List<Item> findByDeletedIsFalse();
}
