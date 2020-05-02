package am.rockstars.controller;

import am.rockstars.dto.CreateProductRequest;
import am.rockstars.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
