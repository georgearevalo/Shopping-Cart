package products.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductFakestoreapi {
  private int id;
  private String title;
  private double price;
  private String description;
  private String category;
  private String image;
}