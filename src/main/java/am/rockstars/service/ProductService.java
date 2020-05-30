package am.rockstars.service;

import am.rockstars.dto.ProductPayload;
import am.rockstars.entity.Product;
import am.rockstars.exception.ProductNotFoundForIdException;
import am.rockstars.mapper.ProductMapper;
import am.rockstars.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger log = getLogger(ProductService.class);

    private final ProductRepository productRepository;

    private final UserService userService;

    private final ProductMapper productMapper;

    @Transactional
    public void createProduct(final String username, final ProductPayload payload) {
        Assert.notNull(payload, "Product payload should not be null");
        log.debug("Creating product for provided payload '{}' username {}", payload, username);
        final Product product = productMapper.mapToProduct(payload);
        product.setCreatedBy(userService.getUserByEmail(username));
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(final Long productId, final ProductPayload payload) {
        final Product storedProduct = findById(productId);
        final Product product = productMapper.mapToProduct(payload);
        product.setId(storedProduct.getId());
        productRepository.save(product);
    }

    @Transactional
    public void removeProduct(final Long productId) {
        productRepository.delete(findById(productId));
    }

    public Product findById(final Long id) {
        Assert.notNull(id, "Id should not be null");
        log.debug("Retrieving product by id '{}'", id);
        return productRepository.findById(id).orElseThrow(() -> {
            log.warn("Not found product for id {} ", id);
            return new ProductNotFoundForIdException(id);
        });
    }
}


