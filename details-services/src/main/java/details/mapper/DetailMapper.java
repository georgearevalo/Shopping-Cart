package details.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import details.model.dto.Detail;
import details.model.DetailResponse;

@Mapper(componentModel = "spring")
public interface DetailMapper {
  
  @Mapping(target = "detailId", source = "id")
  @Mapping(target = "detailOrderId", source = "orderId")
  @Mapping(target = "detailProductId", source = "productId")
  @Mapping(target = "detailQuantity", source = "quantity")
  @Mapping(target = "detailPrice", source = "price")
  DetailResponse detailToDetailResponse(Detail detail);

  @Mapping(target = "id", source = "detailId")
  @Mapping(target = "orderId", source = "detailOrderId")
  @Mapping(target = "productId", source = "detailProductId")
  @Mapping(target = "quantity", source = "detailQuantity")
  @Mapping(target = "price", source = "detailPrice")
  Detail detailResponseToDetail(DetailResponse detail);

  List<DetailResponse> toDTOList(List<Detail> detailsList);

}
