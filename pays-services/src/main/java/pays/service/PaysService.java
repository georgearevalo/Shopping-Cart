package pays.service;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pays.exceptions.OrderNotFoundException;
import pays.exceptions.PaysNotFoundException;
import pays.exceptions.TotalIncorrectException;
import pays.mapper.PayMapper;
import pays.model.PayResponse;
import pays.model.dto.OrderResponse;
import pays.model.dto.Pay;
import pays.infraestructure.OrderServicesApiClient;

/**
 * The {@code PaysService} class provides the business logic for managing pay-related
 * operations. It includes methods for retrieving, saving, updating, and deleting pay
 * information.
 *
 * <p>This service interacts with the {@link PayMapper} to map between {@link Pay}
 * entities and {@link PayResponse} DTOs. It also uses a hardcoded {@link #getInfoPay()}
 * method to simulate retrieving pay data.
 *
 * <p>The service methods throw {@link PaysNotFoundException} when a pay with a given ID
 * is not found.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PaysService {

  private final PayMapper payMapper;
  private final OrderServicesApiClient orderServicesApiClient; 

  /**
   * Retrieves a pay's information based on their ID.
   *
   * @param payId The ID of the pay to retrieve.
   * @return A {@link PayResponse} object containing the pay's information.
   * @throws PaysNotFoundException if no pay is found with the given ID.
   */
  public PayResponse getPay(int payId) {
    PayResponse response = new PayResponse();
    Pay pay = getInfoPay();
    
    if (pay.getId() == payId) {
      response = payMapper.payToPayResponse(pay);
      // Get customer information of services customer
      response.setPayOrderId(orderServicesApiClient.getOrder(pay.getOrderId()).getOrderId());
    } else {
      throw new PaysNotFoundException();
    }
    return response;
  }

  /**
   * Saves a pay.
   *
   * @param pay The pay to save.
   * @return The saved pay.
   */
  public PayResponse savePay(PayResponse pay) {
    pay.setPayId(2);
    OrderResponse order;
    try {
      order = orderServicesApiClient.getOrder(pay.getPayOrderId());
      if (order.getOrderId() == null) {
        throw new OrderNotFoundException();
      }
    } catch (OrderNotFoundException e) {
      throw new OrderNotFoundException();
    }
    
    if (pay.getPayAmount().compareTo(order.getOrderTotal()) != 0) {
      throw new TotalIncorrectException();
    }
    return pay;
  }

  /**
   * Retrieves pay information.
   *
   * @return a Pay object with predefined information.
   */
  protected Pay getInfoPay() {
    Pay pay = new Pay();
    pay.setId(1);
    pay.setOrderId(1);
    pay.setAmount(BigDecimal.valueOf(100.00));
    pay.setDateCreated("2025-03-08");
    return pay;
  }
}
