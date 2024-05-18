package org.ericwubbo.atquerydemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public Iterable<Item> getAll() {
        return itemService.findAllPurchasable();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        if (!itemService.existsById(id)) return ResponseEntity.notFound().build();
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
