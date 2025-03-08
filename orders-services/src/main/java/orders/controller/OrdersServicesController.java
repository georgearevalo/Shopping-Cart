package orders.controller;

import orders.model.*;
import orders.service.OrdersService;
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
public class OrdersServicesController implements OrdersServicesApi {

  private final OrdersService ordersService;

  @Override
  public ResponseEntity<OrderResponse> getOrder(
    @Parameter(name = "OrderId", description = "Order Id", required = true, in = ParameterIn.PATH) @PathVariable("OrderId") Integer orderId) {
    log.info("getOrders method called");
    log.info("OrderId: {}", orderId);
    
    return new ResponseEntity<>(ordersService.getOrder(orderId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<OrderResponse> saveOrder(OrderResponse order) {
    log.info("saveOrder method called");
    log.info("order: {}", order.toString());
    
    return new ResponseEntity<>(ordersService.saveOrder(order), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> updateOrder(OrderResponse order) {
    log.info("updateOrder method called");
    log.info("Order: {}", order.toString());
    
    ordersService.updateOrder(order);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @Override
  public ResponseEntity<Void> deleteOrder(
    @Parameter(name = "OrderId", description = "Order Id", required = true, in = ParameterIn.PATH) @PathVariable("OrderId") Integer orderId) {
    log.info("deleteOrder method called");
    log.info("OrderId: {}", orderId);
    
    ordersService.deleteOrder(orderId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
