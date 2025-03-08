package products.service;

import org.apache.commons.collections4.CollectionUtils;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import products.exceptions.ProductsFakeApiException;
import products.exceptions.ProductsNotFoundException;
import products.infraestructure.AppFakestoreapiClient;
import products.mapper.ProductFakestoreapiMapper;
import products.mapper.ProductMapper;
import products.model.ProductResponse;
import products.model.dto.Product;
import products.model.dto.ProductFakestoreapi;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductsService {

  private final AppFakestoreapiClient appFakestoreapiClient;
  private final ProductFakestoreapiMapper productFakestoreapiMapper;
  private final ProductMapper productMapper;

  /**
   * Retrieves a product's information based on their ID.
   *
   * @param productId The ID of the product to retrieve.
   * @return A {@link ProductResponse} object containing the product's information.
   * @throws ProductsNotFoundException if no product is found with the given ID.
   */
  public ProductResponse getProduct(int productId) {
    return productMapper.productToProductResponse(getProductFake(productId));
  }

  /**
   * Saves a product.
   *
   * @param product The product to save.
   * @return The saved product.
   */
  public ProductResponse saveProduct(ProductResponse product) {
    product.setProductId(21);
    return product;
  }

  /**
   * Updates a product's information based on the provided product ID.
   *
   * @param productId The ID of the product to update.
   * @throws ProductsNotFoundException if the product with the given ID is not found.
   */
  public void updateProduct(ProductResponse product) {
    Product productExist = getProductFake(product.getProductId());
    
    if (productExist.getId() == product.getProductId()) {
      productExist = productMapper.productResponseToProduct(product);
    } else {
      throw new ProductsNotFoundException();
    }
  }

  /**
    * Deletes a product based on the provided product ID.
    *
    * @param productId The ID of the product to delete.
    * @throws ProductsNotFoundException if the product with the given ID is not found.
    */
  public void deleteProduct(Integer productId) {
    ProductResponse response = new ProductResponse();
    Product product = getProductFake(productId);
    
    if (product.getId() == productId) {
      response = productMapper.productToProductResponse(product);
    } else {
      throw new ProductsNotFoundException();
    }
  }

  /**
   * Retrieves a product from the Fakestore API based on the provided product ID.
   *
   * <p>This method fetches all products from the Fakestore API, converts them to DTOs, and then
   * filters the list to find the product matching the given ID.
   *
   * @param productId The ID of the product to retrieve.
   * @return The Product DTO if found.
   * @throws ProductsNotFoundException if no products are found in Fakestore or if the product with
   *     the given ID does not exist.
   * @throws ProductsFakeApiException if an error occurs while fetching products from Fakestore.
   */
  public Product getProductFake(int productId) {
    try {
      List<ProductFakestoreapi> productsFake = appFakestoreapiClient.getAllProducts();
      if (CollectionUtils.isEmpty(productsFake)) {
        log.info("No data found in Fakestore.");
        throw new ProductsNotFoundException();
      }

      log.info("Found {} products in Fakestore.", productsFake.size());
      return productFakestoreapiMapper.toDTOList(productsFake).stream()
          .filter(p -> p.getId() == productId)
          .findFirst()
          .orElseThrow(ProductsNotFoundException::new);

    } catch (ProductsNotFoundException e) {
      throw e;
    } catch (Exception e) {
      log.error("Error occurred while fetching products from Fakestore: ", e);
      throw new ProductsFakeApiException();
    }
  }
}
