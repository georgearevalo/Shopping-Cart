package products.service;

import java.util.Collections;
import org.apache.commons.collections4.CollectionUtils;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import products.infraestructure.AppFakestoreapiClient;
import products.mapper.ProductMapper;
import products.model.ProductResponse;
import products.model.dto.Product;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductsService {

  private final AppFakestoreapiClient appFakestoreapiClient;
  private final ProductMapper productMapper;

  /**
   * @param xProduct product
   * @return found products
   */
  public ProductResponse getProductsResponse() {
    ProductResponse response = new ProductResponse();
    
    try {
      List<Product> products = appFakestoreapiClient.getAllProducts();
      if (CollectionUtils.isEmpty(products)) {
        log.info("No data found in Fakestore.");
        response.setProducts(Collections.emptyList());
        response.setNumberOfProducts(0);
      } else {
        log.info("Found {} products in Fakestore.", products.size());
        response.setProducts(productMapper.toDTOList(products));
        response.setNumberOfProducts(products.size());
      }
    } catch (Exception e) {
      log.error("Error occurred while fetching products from Fakestore: ", e);
      response.setProducts(Collections.emptyList());
      response.setNumberOfProducts(0);
    }
    return response;
  }
}
