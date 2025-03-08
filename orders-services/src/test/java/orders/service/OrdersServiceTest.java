package orders.service;

import orders.exceptions.OrdersNotFoundException;
import orders.infraestructure.CustomerServicesApiClient;
import orders.mapper.OrderMapper;
import orders.model.OrderResponse;
import orders.model.dto.CustomerResponse;
import orders.model.dto.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrdersServiceTest {

  @Mock
  private OrderMapper orderMapper;

  @Mock
  private CustomerServicesApiClient customerServicesApiClient;

  @InjectMocks
  private OrdersService ordersService;

  private Order order;
  private OrderResponse orderResponse;
  private CustomerResponse customerResponse;

  @BeforeEach
  void setUp() {
    // Initialize order and orderResponse objects
    order = new Order();
    order.setId(1);
    order.setCustomerId(1);
    order.setTotal(BigDecimal.valueOf(100.00));
    order.setDateCreated("2025-03-07");

    orderResponse = new OrderResponse();
    orderResponse.setOrderId(1);
    orderResponse.setOrderCustomerId(1);

    customerResponse = new CustomerResponse();
    customerResponse.setCustomerId(1);
  }

  @Test
  void getOrder_ExistingOrderId_ReturnsOrderResponse() {
    when(orderMapper.orderToOrderResponse(order)).thenReturn(orderResponse);
    when(customerServicesApiClient.getCustomer(1)).thenReturn(customerResponse);

    OrderResponse result = ordersService.getOrder(1);

    assertNotNull(result);
    assertEquals(1, result.getOrderId());
  }

  @Test
  void getOrder_NonExistingOrderId_ThrowsOrdersNotFoundException() {
    assertThrows(OrdersNotFoundException.class, () -> ordersService.getOrder(2));
  }

  @Test
  void saveOrder_ValidOrder_ReturnsSavedOrder() {
    OrderResponse inputOrder = new OrderResponse();
    OrderResponse savedOrder = ordersService.saveOrder(inputOrder);

    assertNotNull(savedOrder);
    assertEquals(2, savedOrder.getOrderId());
  }

  @Test
  void updateOrder_ExistingOrderId_UpdatesOrder() {
    OrderResponse orderResponse = new OrderResponse();
    orderResponse.setOrderId(1);

    assertDoesNotThrow(() -> ordersService.updateOrder(orderResponse));
  }

  @Test
  void updateOrder_NonExistingOrderId_ThrowsOrdersNotFoundException() {
    OrderResponse orderResponse = new OrderResponse();
    orderResponse.setOrderId(2);

    assertThrows(OrdersNotFoundException.class, () -> ordersService.updateOrder(orderResponse));
  }

  @Test
  void deleteOrder_ExistingOrderId_DeletesOrder() {
    assertDoesNotThrow(() -> ordersService.deleteOrder(1));
  }

  @Test
  void deleteOrder_NonExistingOrderId_ThrowsOrdersNotFoundException() {
    assertThrows(OrdersNotFoundException.class, () -> ordersService.deleteOrder(2));
  }
}
