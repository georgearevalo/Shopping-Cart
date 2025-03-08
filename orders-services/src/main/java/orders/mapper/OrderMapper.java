package orders.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import orders.model.dto.Order;
import orders.model.OrderResponse;

@Mapper(componentModel = "spring")
public interface OrderMapper {
  
  @Mapping(target = "orderId", source = "id")
  @Mapping(target = "orderCustomerId", source = "customerId")
  @Mapping(target = "orderTotal", source = "total")
  @Mapping(target = "orderDateCreated", source = "dateCreated")
  OrderResponse orderToOrderResponse(Order order);

  @Mapping(target = "id", source = "orderId")
  @Mapping(target = "customerId", source = "orderCustomerId")
  @Mapping(target = "total", source = "orderTotal")
  @Mapping(target = "dateCreated", source = "orderDateCreated")
  Order orderResponseToOrder(OrderResponse order);

  List<OrderResponse> toDTOList(List<Order> ordersList);

}
