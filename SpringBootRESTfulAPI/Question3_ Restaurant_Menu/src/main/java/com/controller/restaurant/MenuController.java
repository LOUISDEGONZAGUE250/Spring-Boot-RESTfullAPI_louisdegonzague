package com.controller.restaurant;

import com.model.restaurant.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class MenuController {

    private final List<MenuItem> items = new ArrayList<>();
    private long nextId = 1L;

    public MenuController() {
        // Initialize at least 8 sample items across categories
        items.add(new MenuItem(nextId++, "Garlic Bread", "Crispy bread with garlic and herb butter", 4.99, "Appetizer",
                true));
        items.add(new MenuItem(nextId++, "Chicken Wings", "Spicy wings with BBQ sauce and ranch dip", 7.99, "Appetizer",
                true));
        items.add(new MenuItem(nextId++, "Grilled Salmon",
                "Fresh Atlantic salmon with lemon butter sauce and seasonal vegetables", 18.99, "Main Course", true));
        items.add(new MenuItem(nextId++, "Beef Steak", "Prime cut beef steak, grilled to perfection with mushrooms",
                24.99, "Main Course", true));
        items.add(new MenuItem(nextId++, "Chocolate Lava Cake",
                "Warm chocolate cake with molten center and vanilla ice cream", 6.99, "Dessert", true));
        items.add(new MenuItem(nextId++, "Cheesecake", "Classic New York style cheesecake with berry compote", 5.99,
                "Dessert", false));
        items.add(
                new MenuItem(nextId++, "Fresh Orange Juice", "Freshly squeezed orange juice", 3.99, "Beverage", true));
        items.add(new MenuItem(nextId++, "Iced Tea", "Refreshing iced tea with lemon", 2.99, "Beverage", true));
    }

    // GET /api/menu
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // GET /api/menu/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        Optional<MenuItem> found = items.stream().filter(i -> i.getId().equals(id)).findFirst();
        return found.map(menuItem -> new ResponseEntity<>(menuItem, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET /api/menu/category/{category}
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> filtered = new ArrayList<>();
        for (MenuItem i : items) {
            if (i.getCategory().equalsIgnoreCase(category)) {
                filtered.add(i);
            }
        }
        if (filtered.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filtered, HttpStatus.OK);
    }

    // GET /api/menu/available?available=true
    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableMenuItems(
            @RequestParam(value = "available", defaultValue = "true") boolean available) {
        List<MenuItem> filtered = new ArrayList<>();
        for (MenuItem i : items) {
            if (i.isAvailable() == available) {
                filtered.add(i);
            }
        }
        if (filtered.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filtered, HttpStatus.OK);
    }

    // GET /api/menu/search?name={name}
    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuItemsByName(@RequestParam(value = "name") String name) {
        List<MenuItem> filtered = new ArrayList<>();
        for (MenuItem i : items) {
            if (i.getName().toLowerCase().contains(name.toLowerCase())) {
                filtered.add(i);
            }
        }
        if (filtered.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filtered, HttpStatus.OK);
    }

    // POST /api/menu
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        menuItem.setId(nextId++);
        items.add(menuItem);
        return new ResponseEntity<>(menuItem, HttpStatus.CREATED);
    }

    // PUT /api/menu/{id}/availability?available=true
    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleMenuItemAvailability(@PathVariable Long id,
            @RequestParam(value = "available") boolean available) {
        for (MenuItem i : items) {
            if (i.getId().equals(id)) {
                i.setAvailable(available);
                return new ResponseEntity<>(i, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE /api/menu/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        Optional<MenuItem> found = items.stream().filter(i -> i.getId().equals(id)).findFirst();
        if (found.isPresent()) {
            items.remove(found.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
