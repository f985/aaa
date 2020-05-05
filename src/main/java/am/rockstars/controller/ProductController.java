package am.rockstars.controller;

import am.rockstars.dto.ProductPayload;
import am.rockstars.security.util.SecurityUtils;
import am.rockstars.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody final ProductPayload productPayload) {
        productService.createProduct(SecurityUtils.getCurrentUserUsername(), productPayload);
    }
}
