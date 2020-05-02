package am.rockstars.service;

import am.rockstars.dto.CreateProductRequest;
import am.rockstars.dto.ProductBean;
import am.rockstars.entity.Product;
import am.rockstars.mapper.ProductMapper;
import am.rockstars.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductBean createProduct(CreateProductRequest productRequest) {
        Product createdProduct = productRepository.save(productMapper.map(productRequest));
        return productMapper.map(createdProduct);
    }

    public List<ProductBean> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::map).
                        collect(Collectors.toList());
    }
}
