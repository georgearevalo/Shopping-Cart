package products.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import products.infraestructure.AppFakestoreapiClient;
import products.mapper.ProductMapper;
import products.model.ProductResponse;
import products.model.dto.Product;
import products.model.ProductsDTO;


@ExtendWith(MockitoExtension.class)
public class ProductsServiceTest {

  @Mock
  private AppFakestoreapiClient appFakestoreapiClient;

  @Mock
  private ProductMapper productMapper;

  @InjectMocks
  private ProductsService productsService;

  @Test
  void getProductsResponse_shouldReturnProducts_whenProductsFound() {
    // Arrange
    Product product1 = new Product();
    Product product2 = new Product();
    List<Product> products = Arrays.asList(product1, product2);

    ProductsDTO productDTO1 = new ProductsDTO();
    ProductsDTO productDTO2 = new ProductsDTO();
    List<ProductsDTO> productDTOs = Arrays.asList(productDTO1, productDTO2);

    when(appFakestoreapiClient.getAllProducts()).thenReturn(products);
    when(productMapper.toDTOList(products)).thenReturn(productDTOs);

    // Act
    ProductResponse response = productsService.getProductsResponse();

    // Assert
    assertEquals(2, response.getNumberOfProducts());
    assertEquals(productDTOs, response.getProducts());
  }

  @Test
  void getProductsResponse_shouldReturnEmptyList_whenNoProductsFound() {
    // Arrange
    when(appFakestoreapiClient.getAllProducts()).thenReturn(Collections.emptyList());

    // Act
    ProductResponse response = productsService.getProductsResponse();

    // Assert
    assertEquals(0, response.getNumberOfProducts());
    assertEquals(Collections.emptyList(), response.getProducts());
  }

  @Test
  void getProductsResponse_shouldReturnEmptyList_whenExceptionOccurs() {
    // Arrange
    when(appFakestoreapiClient.getAllProducts()).thenThrow(new RuntimeException("Simulated exception"));

    // Act
    ProductResponse response = productsService.getProductsResponse();

    // Assert
    assertEquals(0, response.getNumberOfProducts());
    assertEquals(Collections.emptyList(), response.getProducts());
  }
}