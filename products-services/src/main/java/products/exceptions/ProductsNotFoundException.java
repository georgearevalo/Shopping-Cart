package products.exceptions;

import org.springframework.http.HttpStatus;

import products.exceptions.catalog.ProductsExceptionCatalog;

public class ProductsNotFoundException extends ProductsException {

  public ProductsNotFoundException() {
    super(HttpStatus.NOT_FOUND,
      ProductsExceptionCatalog.PRODUCTS_NOT_FOUND_ERROR_CODE,
      ProductsExceptionCatalog.PRODUCTS_NOT_FOUND_ERROR_MESSAGE);
  }

}
