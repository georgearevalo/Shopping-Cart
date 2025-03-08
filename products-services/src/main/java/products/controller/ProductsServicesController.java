package products.controller;

import products.model.*;
import products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductsServicesController implements ProductsServicesApi {

  private final ProductsService productsService;
  
  @Override
  public ResponseEntity<ProductResponse> getProduct(
    @Parameter(name = "ProductId", description = "Product Id", required = true, in = ParameterIn.PATH) @PathVariable("ProductId") Integer productId) {
    log.info("getProducts method called");
    log.info("ProductId: {}", productId);
    
    return new ResponseEntity<>(productsService.getProduct(productId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ProductResponse> saveProduct(ProductResponse product) {
    log.info("saveProduct method called");
    log.info("product: {}", product.toString());
    
    return new ResponseEntity<>(productsService.saveProduct(product), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> updateProduct(ProductResponse product) {
    log.info("updateProduct method called");
    log.info("Product: {}", product.toString());
    
    productsService.updateProduct(product);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @Override
  public ResponseEntity<Void> deleteProduct(
    @Parameter(name = "ProductId", description = "Product Id", required = true, in = ParameterIn.PATH) @PathVariable("ProductId") Integer productId) {
    log.info("deleteProduct method called");
    log.info("ProductId: {}", productId);
    
    productsService.deleteProduct(productId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
