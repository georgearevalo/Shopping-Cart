package pays.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pays.model.dto.Pay;
import pays.model.PayResponse;

@Mapper(componentModel = "spring")
public interface PayMapper {
  
  @Mapping(target = "payId", source = "id")
  @Mapping(target = "payOrderId", source = "orderId")
  @Mapping(target = "payAmount", source = "amount")
  @Mapping(target = "payDateCreated", source = "dateCreated")
  PayResponse payToPayResponse(Pay pay);

  @Mapping(target = "id", source = "payId")
  @Mapping(target = "orderId", source = "payOrderId")
  @Mapping(target = "amount", source = "payAmount")
  @Mapping(target = "dateCreated", source = "payDateCreated")
  Pay payResponseToPay(PayResponse pay);

  List<PayResponse> toDTOList(List<Pay> paysList);

}
