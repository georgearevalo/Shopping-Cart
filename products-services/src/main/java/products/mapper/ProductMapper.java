package products.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import products.model.dto.Product;
import products.model.ProductsDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  
  @Mapping(target = "productId", source = "id")
  @Mapping(target = "productName", source = "title")
  @Mapping(target = "productPrice", source = "price")
  @Mapping(target = "productDescription", source = "description")
  @Mapping(target = "productCategory", source = "category")
  @Mapping(target = "productImage", source = "image")
  ProductsDTO productToProductsDTO(Product product);

  List<ProductsDTO> toDTOList(List<Product> productsList);

}
