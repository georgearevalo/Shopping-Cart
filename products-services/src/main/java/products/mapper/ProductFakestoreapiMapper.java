package products.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import products.model.dto.ProductFakestoreapi;
import products.model.dto.Product;

@Mapper(componentModel = "spring")
public interface ProductFakestoreapiMapper {
  
  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "title")
  @Mapping(target = "price", source = "price")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "category", source = "category")
  @Mapping(target = "image", source = "image")
  Product productFakestoreapiToProduct(ProductFakestoreapi product);

  List<Product> toDTOList(List<ProductFakestoreapi> productsList);

}
