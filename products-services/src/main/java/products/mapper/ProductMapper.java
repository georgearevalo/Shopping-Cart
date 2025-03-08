package products.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import products.model.dto.Product;
import products.model.ProductResponse;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  
  @Mapping(target = "productId", source = "id")
  @Mapping(target = "productName", source = "name")
  @Mapping(target = "productPrice", source = "price")
  @Mapping(target = "productDescription", source = "description")
  @Mapping(target = "productCategory", source = "category")
  @Mapping(target = "productImage", source = "image")
  ProductResponse productToProductResponse(Product product);

  @Mapping(target = "id", source = "productId")
  @Mapping(target = "name", source = "productName")
  @Mapping(target = "price", source = "productPrice")
  @Mapping(target = "description", source = "productDescription")
  @Mapping(target = "category", source = "productCategory")
  @Mapping(target = "image", source = "productImage")
  Product productResponseToProduct(ProductResponse detail);

  List<ProductResponse> toDTOList(List<Product> productsList);

}
