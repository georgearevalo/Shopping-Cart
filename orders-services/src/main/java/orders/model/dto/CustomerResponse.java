package orders.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {  
  private Integer customerId;
  private String customerName;
  private String customerEmail;
  private String customerAddress;
}
