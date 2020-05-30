package am.rockstars.service;

import am.rockstars.dto.ProductPayload;
import am.rockstars.entity.Product;
import am.rockstars.entity.User;
import am.rockstars.exception.ProductNotFoundForIdException;
import am.rockstars.repository.ProductRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ProductServiceTest extends AbstractServiceUnitTest {

    @InjectMocks
    @Spy
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserService userService;

    @DisplayName("Should create product for provided payload")
    @Test
    void createProduct() {
        //Test data
        final User testUser = createTestUser("Sergey");
        final EasyRandom easyRandom = new EasyRandom();
        final ProductPayload productPayload = easyRandom.nextObject(ProductPayload.class);
        //Mock
        when(userService.getUserByEmail(eq("Sergey"))).thenReturn(testUser);
        when(productRepository.save(any(Product.class))).then(invocationOnMock -> invocationOnMock.getArgument(0));
        //Service call
        productService.createProduct("Sergey", productPayload);
        //Verify
        verify(userService).getUserByEmail(eq("Sergey"));
        final ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());
        verifyNoMoreInteractions(userService, productRepository);
        //Asserts
        assertThat(productArgumentCaptor.getValue()).isEqualToIgnoringGivenFields(productPayload, "createdBy", "id", "createdAt", "updatedAt");
    }

    @DisplayName("Should throw exception when product payload is null")
    @Test
    void createProductWithInvalidArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.createProduct("Sergey", null), "Expected to throw IllegalArgumentException");
    }

    @DisplayName("Should return product for provided id")
    @Test
    void findById() {
        //Mock
        when(productRepository.findById(eq(1L))).thenReturn(Optional.of(new Product()));
        //Service call
        final Product result = productService.findById(1L);
        //Verify
        verify(productRepository).findById(eq(1L));
        verifyNoMoreInteractions(productRepository);
        //Asserts
        assertThat(result).isNotNull();
    }

    @DisplayName("Should throw exception when id is null or product was not found")
    @Test
    void findProductWithIllegalArguments() {
        assertThrows(IllegalArgumentException.class, () -> productService.findById(null), "Should throw IllegalArgumentException");
        assertThrows(ProductNotFoundForIdException.class, () -> {
            when(productRepository.findById(eq(1L))).thenReturn(Optional.empty());
            productService.findById(1L);
        }, "Should throw ProductNotFoundForIdException");
    }

    @DisplayName("Should update product for provided payload")
    @Test
    void updateProduct() {
        //Test data
        final EasyRandom easyRandom = new EasyRandom();
        final ProductPayload productPayload = easyRandom.nextObject(ProductPayload.class);
        //Mock
        doReturn(new Product()).when(productService).findById(eq(1L));
        when(productRepository.save(any(Product.class))).then(invocation -> invocation.getArgument(0));
        //Service call
        productService.updateProduct(1L, productPayload);
        //Verify
        verify(productService).findById(eq(1L));
        verify(productService).updateProduct(anyLong(), any(ProductPayload.class));
        final ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());
        verifyNoMoreInteractions(productService, productRepository);
        //Asserts
        assertThat(productArgumentCaptor.getValue()).isEqualToIgnoringGivenFields(productPayload, "createdBy", "id", "createdAt", "updatedAt");
    }

    @DisplayName("Should remove product for provided id")
    @Test
    void deleteProduct() {
        //Mock
        doReturn(new Product()).when(productService).findById(eq(1L));
        //Service call
        productService.removeProduct(1L);
        //Verify
        verify(productService).findById(eq(1L));
        verify(productService).removeProduct(eq(1L));
        verify(productRepository).delete(notNull());
        verifyNoMoreInteractions(productRepository, productService);
    }
}
