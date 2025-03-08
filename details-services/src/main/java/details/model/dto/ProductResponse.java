package details.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {  
  private Integer productId;
  private String productName;
  private Double productPrice;
  private String productDescription;
  private String productCategory;
  private String productImage;
}
