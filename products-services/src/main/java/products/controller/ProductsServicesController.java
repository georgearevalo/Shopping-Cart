package products.controller;

import products.model.*;
import products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductsServicesController implements ProductsServicesApi {

  private final ProductsService productsService;

  @Override
  public ResponseEntity<ProductResponse> getProducts(String xProduct) {
    log.info("getProducts method called");
    log.info("Product: {}", xProduct);

    return new ResponseEntity<>(productsService.getProductsResponse(), HttpStatus.OK);

  }
}
