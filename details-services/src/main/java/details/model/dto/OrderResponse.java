package details.model.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {  
  private Integer orderId;
  private Integer orderCustomerId;
  private BigDecimal orderTotal;
  private String orderDateCreated;
}
