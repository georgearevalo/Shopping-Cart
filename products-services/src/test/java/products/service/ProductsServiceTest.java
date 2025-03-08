package products.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import products.exceptions.ProductsFakeApiException;
import products.exceptions.ProductsNotFoundException;
import products.infraestructure.AppFakestoreapiClient;
import products.mapper.ProductFakestoreapiMapper;
import products.mapper.ProductMapper;
import products.model.ProductResponse;
import products.model.dto.Product;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTest {

  @Mock private AppFakestoreapiClient appFakestoreapiClient;
  @Mock private ProductFakestoreapiMapper productFakestoreapiMapper;
  @Mock private ProductMapper productMapper;

  @InjectMocks private ProductsService productsService;

  @Test
  void getProduct_ExistingProductId_ReturnsProductResponse() {
    int productId = 1;
    Product product = new Product();
    product.setId(productId);
    ProductResponse productResponse = new ProductResponse();
    productResponse.setProductId(productId);
  }

  @Test
  void saveProduct_ValidProduct_ReturnsSavedProduct() {
    ProductResponse product = new ProductResponse();
    ProductResponse savedProduct = productsService.saveProduct(product);

    assertEquals(21, savedProduct.getProductId());
  }

  @Test
  void updateProduct_ExistingProduct_UpdatesProduct() {
    ProductResponse productResponse = new ProductResponse();
    int productId = 1;
    productResponse.setProductId(productId);
    Product product = new Product();
    product.setId(productId);
  }

  @Test
  void updateProduct_NonExistingProduct_ThrowsProductsNotFoundException() {
    ProductResponse product = new ProductResponse();
    product.setProductId(1);
  }

  @Test
  void deleteProduct_ExistingProduct_DeletesProduct() {
    int productId = 1;
    Product product = new Product();
    product.setId(productId);
  }

  @Test
  void deleteProduct_NonExistingProduct_ThrowsProductsNotFoundException() {
    int productId = 1;
    assertThrows(ProductsNotFoundException.class, () -> productsService.deleteProduct(productId));
  }

  @Test
  void getProductFake_NoProductsFound_ThrowsProductsNotFoundException() {
    when(appFakestoreapiClient.getAllProducts()).thenReturn(Collections.emptyList());

    assertThrows(ProductsNotFoundException.class, () -> productsService.getProductFake(1));
    verify(appFakestoreapiClient, times(1)).getAllProducts();
  }

  @Test
  void getProductFake_ApiException_ThrowsProductsFakeApiException() {
    when(appFakestoreapiClient.getAllProducts()).thenThrow(new RuntimeException());

    assertThrows(ProductsFakeApiException.class, () -> productsService.getProductFake(1));
    verify(appFakestoreapiClient, times(1)).getAllProducts();
  }
}