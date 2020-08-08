package am.rockstars.controller;

import am.rockstars.dto.GetProductDetailedInfoResponse;
import am.rockstars.dto.ProductPayload;
import am.rockstars.entity.Product;
import am.rockstars.mapper.ProductMapper;
import am.rockstars.repository.ProductRepository;
import am.rockstars.response.ProductResponse;
import am.rockstars.service.ProductService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Api
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    private final ProductMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody final ProductPayload productPayload) {
        productService.createProduct(SecurityContextHolder.getContext().getAuthentication().getName(), productPayload);
    }

    @ApiOperation(value = "Retrieve product by id", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{productId}")
    public GetProductDetailedInfoResponse findById(@PathVariable final Long productId) {
        return mapper.mapToProductDetailedInfoResponse(productService.findById(productId));
    }

    @ApiOperation(value = "Retrieve products for search predicate", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public Page<ProductResponse> retrieveProducts(@QuerydslPredicate(root = Product.class) final Predicate searchPredicate, final Pageable pageable) {
        return productRepository.findAll(searchPredicate, pageable).map(mapper::mapToProductResponse);
    }

    @ApiOperation(value = "Update product")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{productId}")
    public void updateProduct(@PathVariable final Long productId, @Valid @RequestBody final ProductPayload productPayload) {
        productService.updateProduct(productId, productPayload);
    }

    @ApiOperation(value = "Remove product")
    @DeleteMapping("/{productId}")
    public void removeProduct(@PathVariable final Long productId) {
        productService.removeProduct(productId);
    }
}
