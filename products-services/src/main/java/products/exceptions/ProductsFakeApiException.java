package products.exceptions;

import org.springframework.http.HttpStatus;

import products.exceptions.catalog.ProductsExceptionCatalog;

public class ProductsFakeApiException extends ProductsException {

  public ProductsFakeApiException() {
    super(HttpStatus.NOT_FOUND,
      ProductsExceptionCatalog.PRODUCTS_FAKE_API_ERROR_CODE,
      ProductsExceptionCatalog.PRODUCTS_FAKE_API_ERROR_MESSAGE);
  }

}
