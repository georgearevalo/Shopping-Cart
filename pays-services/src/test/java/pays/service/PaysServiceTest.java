package pays.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pays.exceptions.OrderNotFoundException;
import pays.exceptions.PaysNotFoundException;
import pays.exceptions.TotalIncorrectException;
import pays.mapper.PayMapper;
import pays.model.PayResponse;
import pays.model.dto.OrderResponse;
import pays.model.dto.Pay;
import pays.infraestructure.OrderServicesApiClient;

@ExtendWith(MockitoExtension.class)
class PaysServiceTest {

  @Mock
  private PayMapper payMapper;

  @Mock
  private OrderServicesApiClient orderServicesApiClient;

  @InjectMocks
  private PaysService paysService;

  private Pay mockPay;
  private PayResponse mockPayResponse;
  private OrderResponse mockOrderResponse;

  @BeforeEach
  void setUp() {
    mockPay = new Pay();
    mockPay.setId(1);
    mockPay.setOrderId(1);
    mockPay.setAmount(BigDecimal.valueOf(100.00));
    mockPay.setDateCreated("2025-03-08");

    mockPayResponse = new PayResponse();
    mockPayResponse.setPayId(1);
    mockPayResponse.setPayOrderId(1);
    mockPayResponse.setPayAmount(BigDecimal.valueOf(100.00));

    mockOrderResponse = new OrderResponse();
    mockOrderResponse.setOrderId(1);
    mockOrderResponse.setOrderTotal(BigDecimal.valueOf(100.00));
  }

  @Test
  void getPay_ExistingPayId_ReturnsPayResponse() {
    when(payMapper.payToPayResponse(any(Pay.class))).thenReturn(mockPayResponse);
    when(orderServicesApiClient.getOrder(1)).thenReturn(mockOrderResponse);

    PayResponse result = paysService.getPay(1);

    assertNotNull(result);
    assertEquals(1, result.getPayOrderId());
  }

  @Test
  void getPay_NonExistingPayId_ThrowsPaysNotFoundException() {
    assertThrows(PaysNotFoundException.class, () -> paysService.getPay(2));
  }

  @Test
  void savePay_ValidPay_ReturnsSavedPay() {
    when(orderServicesApiClient.getOrder(anyInt())).thenReturn(mockOrderResponse);

    PayResponse result = paysService.savePay(mockPayResponse);

    assertNotNull(result);
    assertEquals(2, result.getPayId());
  }

  @Test
  void savePay_OrderNotFound_ThrowsOrderNotFoundException() {
    when(orderServicesApiClient.getOrder(anyInt())).thenThrow(new OrderNotFoundException());

    assertThrows(OrderNotFoundException.class, () -> paysService.savePay(mockPayResponse));
  }

  @Test
  void savePay_TotalIncorrect_ThrowsTotalIncorrectException() {
    OrderResponse incorrectOrderResponse = new OrderResponse();
    incorrectOrderResponse.setOrderId(1);
    incorrectOrderResponse.setOrderTotal(BigDecimal.valueOf(200.00));

    when(orderServicesApiClient.getOrder(anyInt())).thenReturn(incorrectOrderResponse);

    assertThrows(TotalIncorrectException.class, () -> paysService.savePay(mockPayResponse));
  }

  @Test
  void getInfoPay_ReturnsPay() {
    Pay result = paysService.getInfoPay();

    assertNotNull(result);
    assertEquals(1, result.getId());
    assertEquals(1, result.getOrderId());
    assertEquals(BigDecimal.valueOf(100.00), result.getAmount());
    assertEquals("2025-03-08", result.getDateCreated());
  }
}