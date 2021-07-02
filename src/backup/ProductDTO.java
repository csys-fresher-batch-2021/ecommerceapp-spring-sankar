package in.sankarvinoth.ecommerce_app.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDTO {
private String productName; 
private String productId;
private String category;
private int price;
private int quantity;
private String status;
}
