package org.ericwubbo.atquerydemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Iterable<Item> findAll() {
        return itemRepository.findByDeleted(false);
    }

    public boolean existsById(long id) {
        Optional<Item> possibleItem = itemRepository.findById(id);
        return possibleItem.isPresent() && !possibleItem.get().isDeleted();
    }

    public void deleteById(long id) {
        Optional<Item> possibleItem = itemRepository.findById(id);
        if (possibleItem.isEmpty() || possibleItem.get().isDeleted())
            throw new IllegalArgumentException("Item does not exist");
        Item item = possibleItem.get();
        item.setDeleted(true);
        itemRepository.save(item);
    }
}
