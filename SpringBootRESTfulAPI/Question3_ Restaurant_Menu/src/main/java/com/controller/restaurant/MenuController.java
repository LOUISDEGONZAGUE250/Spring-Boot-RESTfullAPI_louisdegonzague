package com.controller.restaurant;

import com.model.restaurant.MenuItem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class MenuController {

    private List<MenuItem> items = new ArrayList<>();
    private long nextId = 1;

    public MenuController() {

        items.add(new MenuItem(nextId++, "Garlic Bread",
                "Crispy bread with garlic and butter", 4.99, "Appetizer", true));

        items.add(new MenuItem(nextId++, "Chicken Wings",
                "Spicy chicken wings with sauce", 7.99, "Appetizer", true));

        items.add(new MenuItem(nextId++, "Grilled Salmon",
                "Grilled salmon with vegetables", 18.99, "Main Course", true));

        items.add(new MenuItem(nextId++, "Beef Steak",
                "Grilled beef steak", 24.99, "Main Course", true));

        items.add(new MenuItem(nextId++, "Chocolate Cake",
                "Chocolate cake with ice cream", 6.99, "Dessert", true));

        items.add(new MenuItem(nextId++, "Cheesecake",
                "Cheesecake with berries", 5.99, "Dessert", false));

        items.add(new MenuItem(nextId++, "Orange Juice",
                "Fresh orange juice", 3.99, "Beverage", true));

        items.add(new MenuItem(nextId++, "Iced Tea",
                "Cold iced tea", 2.99, "Beverage", true));
    }

    // Get all menu items
    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return items;
    }

    // Get menu item by ID
    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Long id) {
        for (MenuItem item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    // Get menu items by category
    @GetMapping("/category/{category}")
    public List<MenuItem> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : items) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return result;
    }

    // Get available or unavailable items
    @GetMapping("/available")
    public List<MenuItem> getAvailableMenuItems(
            @RequestParam(defaultValue = "true") boolean available) {

        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : items) {
            if (item.isAvailable() == available) {
                result.add(item);
            }
        }
        return result;
    }

    // Search menu items by name
    @GetMapping("/search")
    public List<MenuItem> searchMenuItemsByName(@RequestParam String name) {
        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : items) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }

    // Add new menu item
    @PostMapping
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        menuItem.setId(nextId++);
        items.add(menuItem);
        return menuItem;
    }

    // Update availability
    @PutMapping("/{id}/availability")
    public MenuItem updateAvailability(@PathVariable Long id,
                                       @RequestParam boolean available) {
        for (MenuItem item : items) {
            if (item.getId().equals(id)) {
                item.setAvailable(available);
                return item;
            }
        }
        return null;
    }

    // Delete menu item
    @DeleteMapping("/{id}")
    public String deleteMenuItem(@PathVariable Long id) {
        for (MenuItem item : items) {
            if (item.getId().equals(id)) {
                items.remove(item);
                return "Menu item deleted";
            }
        }
        return "Menu item not found";
    }
}
