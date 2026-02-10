package controller.ecommerce;

import model.ecommerce.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Initialize sample data
    private static List<Product> products = new ArrayList<>();
    private static Long nextId = 11L;

    static {
        // Initialize with 10 sample products
        products.add(new Product(1L, "Laptop Pro", "High-performance laptop with Intel i7 processor", 1299.99,
                "Electronics", 15, "Dell"));
        products.add(new Product(2L, "Wireless Mouse", "Ergonomic wireless mouse with USB receiver", 29.99,
                "Electronics", 50, "Logitech"));
        products.add(new Product(3L, "USB-C Cable", "Fast charging USB-C cable 2 meters long", 12.99, "Electronics",
                100, "Anker"));
        products.add(new Product(4L, "Gaming Headset", "Surround sound gaming headset with noise cancellation", 149.99,
                "Electronics", 25, "SteelSeries"));
        products.add(new Product(5L, "Running Shoes", "Professional running shoes with cushioning technology", 89.99,
                "Sports", 30, "Nike"));
        products.add(new Product(6L, "Yoga Mat", "Non-slip yoga mat 6mm thickness", 25.99, "Sports", 40, "Liforme"));
        products.add(new Product(7L, "Cotton T-Shirt", "100% organic cotton comfortable t-shirt", 19.99, "Clothing", 60,
                "H&M"));
        products.add(new Product(8L, "Denim Jeans", "Classic blue denim jeans with stretch fabric", 49.99, "Clothing",
                35, "Levi's"));
        products.add(new Product(9L, "Stainless Steel Water Bottle",
                "Insulated water bottle keeps drinks hot for 12 hours", 34.99, "Sports", 45, "Hydro Flask"));
        products.add(new Product(10L, "4K Webcam", "Ultra HD 4K webcam for streaming and video calls", 179.99,
                "Electronics", 20, "Razer"));
    }

    // GET all products with optional pagination
    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        int startIndex = (page - 1) * limit;
        int endIndex = Math.min(startIndex + limit, products.size());

        if (startIndex >= products.size()) {
            return new ArrayList<>();
        }
        return products.subList(startIndex, endIndex);
    }

    // GET product by ID
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    // GET products by category
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // GET products by brand
    @GetMapping("/brand/{brand}")
    public List<Product> getProductsByBrand(@PathVariable String brand) {
        return products.stream()
                .filter(p -> p.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    // GET products by search keyword
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerKeyword) ||
                        p.getDescription().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    // GET products within price range
    @GetMapping("/price-range")
    public List<Product> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    // GET products in stock
    @GetMapping("/in-stock")
    public List<Product> getInStockProducts() {
        return products.stream()
                .filter(p -> p.getStockQuantity() > 0)
                .collect(Collectors.toList());
    }

    // POST - Add new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        product.setProductId(nextId++);
        products.add(product);
        return product;
    }

    // PUT - Update product
    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                updatedProduct.setProductId(productId);
                products.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    // PATCH - Update stock quantity
    @PatchMapping("/{productId}/stock")
    public Product updateStockQuantity(@PathVariable Long productId, @RequestParam int quantity) {
        Product product = getProductById(productId);
        if (product != null) {
            product.setStockQuantity(quantity);
            return product;
        }
        return null;
    }

    // DELETE - Delete product
    @DeleteMapping("/{productId}")
    public boolean deleteProduct(@PathVariable Long productId) {
        return products.removeIf(p -> p.getProductId().equals(productId));
    }
}
