package in.sankarvinoth.ecommerce_app.model;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;
@Component
@Data
@ToString
public class Product {
private String productId;
private String productName;
private String category;
private int price;
private int quantity;
private String status;


}