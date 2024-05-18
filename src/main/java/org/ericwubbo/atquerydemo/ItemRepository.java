package org.ericwubbo.atquerydemo;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Override
    @Nonnull
    default List<Item> findAll() {
        return findByHasBeenDeleted(false);
    }

    @Override
    default void deleteById(@Nonnull Long id) {
        Optional<Item> possibleItem = findById(id);
        if (possibleItem.isEmpty()) throw new IllegalArgumentException("Item with id " + id + " does not exist!");
        Item item = possibleItem.get();
        item.setHasBeenDeleted(true);
        save(item);
    }

    List<Item> findByHasBeenDeleted(boolean b);
}
