package details.service;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import details.exceptions.DetailsNotFoundException;
import details.mapper.DetailMapper;
import details.model.DetailResponse;
import details.model.dto.Detail;
import details.infraestructure.OrdersServicesApiClient;
import details.infraestructure.ProductsServicesApiClient;

/**
 * The {@code DetailsService} class provides the business logic for managing detail-related
 * operations. It includes methods for retrieving, saving, updating, and deleting detail
 * information.
 *
 * <p>This service interacts with the {@link DetailMapper} to map between {@link Detail}
 * entities and {@link DetailResponse} DTOs. It also uses a hardcoded {@link #getInfoDetail()}
 * method to simulate retrieving detail data.
 *
 * <p>The service methods throw {@link DetailsNotFoundException} when a detail with a given ID
 * is not found.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DetailsService {

  private final DetailMapper detailMapper;
  private final OrdersServicesApiClient ordersServicesApiClient;
  private final ProductsServicesApiClient productsServicesApiClient;

  /**
   * Retrieves a detail's information based on their ID.
   *
   * @param detailId The ID of the detail to retrieve.
   * @return A {@link DetailResponse} object containing the detail's information.
   * @throws DetailsNotFoundException if no detail is found with the given ID.
   */
  public DetailResponse getDetail(int detailId) {
    DetailResponse response = new DetailResponse();
    Detail detail = getInfoDetail();
    
    if (detail.getId() == detailId) {
      response = detailMapper.detailToDetailResponse(detail);
      // Get order information of services orders
      response.setDetailOrderId(ordersServicesApiClient.getOrder(detail.getOrderId()).getOrderId());
      // Get product information of services products
      response.setDetailProductId(productsServicesApiClient.getProduct(detail.getProductId()).getProductId());
    } else {
      throw new DetailsNotFoundException();
    }
    return response;
  }

  /**
   * Saves a detail.
   *
   * @param detail The detail to save.
   * @return The saved detail.
   */
  public DetailResponse saveDetail(DetailResponse detail) {
    detail.setDetailId(2);
    return detail;
  }

  /**
   * Updates a detail's information based on the provided detail ID.
   *
   * @param detailId The ID of the detail to update.
   * @throws DetailsNotFoundException if the detail with the given ID is not found.
   */
  public void updateDetail(DetailResponse detail) {
    Detail detailExist = getInfoDetail();
    
    if (detailExist.getId() == detail.getDetailId()) {
      detailExist = detailMapper.detailResponseToDetail(detail);
    } else {
      throw new DetailsNotFoundException();
    }
  }

  /**
    * Deletes a detail based on the provided detail ID.
    *
    * @param detailId The ID of the detail to delete.
    * @throws DetailsNotFoundException if the detail with the given ID is not found.
    */
  public void deleteDetail(Integer detailId) {
    DetailResponse response = new DetailResponse();
    Detail detail = getInfoDetail();
    
    if (detail.getId() == detailId) {
      response = detailMapper.detailToDetailResponse(detail);
    } else {
      throw new DetailsNotFoundException();
    }
  }

  /**
   * Retrieves detail information.
   *
   * @return a Detail object with predefined information.
   */
  protected Detail getInfoDetail() {
    Detail detail = new Detail();
    detail.setId(1);
    detail.setOrderId(1);
    detail.setProductId(5);
    detail.setQuantity(10);
    detail.setPrice(BigDecimal.valueOf(10.00));
    return detail;
  }
}
