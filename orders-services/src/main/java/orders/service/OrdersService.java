package orders.service;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import orders.exceptions.OrdersNotFoundException;
import orders.mapper.OrderMapper;
import orders.model.OrderResponse;
import orders.model.dto.Order;
import orders.infraestructure.CustomerServicesApiClient;

/**
 * The {@code OrdersService} class provides the business logic for managing order-related
 * operations. It includes methods for retrieving, saving, updating, and deleting order
 * information.
 *
 * <p>This service interacts with the {@link OrderMapper} to map between {@link Order}
 * entities and {@link OrderResponse} DTOs. It also uses a hardcoded {@link #getInfoOrder()}
 * method to simulate retrieving order data.
 *
 * <p>The service methods throw {@link OrdersNotFoundException} when a order with a given ID
 * is not found.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrdersService {

  private final OrderMapper orderMapper;
  private final CustomerServicesApiClient customerServicesApiClient; 

  /**
   * Retrieves a order's information based on their ID.
   *
   * @param orderId The ID of the order to retrieve.
   * @return A {@link OrderResponse} object containing the order's information.
   * @throws OrdersNotFoundException if no order is found with the given ID.
   */
  public OrderResponse getOrder(int orderId) {
    OrderResponse response = new OrderResponse();
    Order order = getInfoOrder();
    
    if (order.getId() == orderId) {
      response = orderMapper.orderToOrderResponse(order);
      // Get customer information of services customer
      response.setOrderCustomerId(customerServicesApiClient.getCustomer(order.getCustomerId()).getCustomerId());
    } else {
      throw new OrdersNotFoundException();
    }
    return response;
  }

  /**
   * Saves a order.
   *
   * @param order The order to save.
   * @return The saved order.
   */
  public OrderResponse saveOrder(OrderResponse order) {
    order.setOrderId(2);
    return order;
  }

  /**
   * Updates a order's information based on the provided order ID.
   *
   * @param orderId The ID of the order to update.
   * @throws OrdersNotFoundException if the order with the given ID is not found.
   */
  public void updateOrder(OrderResponse order) {
    Order orderExist = getInfoOrder();
    
    if (orderExist.getId() == order.getOrderId()) {
      orderExist = orderMapper.orderResponseToOrder(order);
    } else {
      throw new OrdersNotFoundException();
    }
  }

  /**
    * Deletes a order based on the provided order ID.
    *
    * @param orderId The ID of the order to delete.
    * @throws OrdersNotFoundException if the order with the given ID is not found.
    */
  public void deleteOrder(Integer orderId) {
    OrderResponse response = new OrderResponse();
    Order order = getInfoOrder();
    
    if (order.getId() == orderId) {
      response = orderMapper.orderToOrderResponse(order);
    } else {
      throw new OrdersNotFoundException();
    }
  }

  /**
   * Retrieves order information.
   *
   * @return a Order object with predefined information.
   */
  protected Order getInfoOrder() {
    Order order = new Order();
    order.setId(1);
    order.setCustomerId(1);
    order.setTotal(BigDecimal.valueOf(100.00));
    order.setDateCreated("2025-03-07");
    return order;
  }
}
