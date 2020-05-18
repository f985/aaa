package am.rockstars.controller;

import am.rockstars.dto.ProductPayload;
import am.rockstars.mapper.ProductMapper;
import am.rockstars.response.ProductResponse;
import am.rockstars.security.util.SecurityUtils;
import am.rockstars.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Api
public class ProductController {

    private final ProductService productService;

    private final ProductMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody final ProductPayload productPayload) {
        productService.createProduct(SecurityUtils.getCurrentUserUsername(), productPayload);
    }

    @ApiOperation(value = "Retrieve product by id", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{productId}")
    public ProductResponse name(@PathVariable final Long productId) {
        return mapper.mapToProductResponse(productService.findById(productId));
    }
}
