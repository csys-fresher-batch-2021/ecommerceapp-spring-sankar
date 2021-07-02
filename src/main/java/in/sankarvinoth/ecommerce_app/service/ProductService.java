package in.sankarvinoth.ecommerce_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sankarvinoth.ecommerce_app.commons.Message;
import in.sankarvinoth.ecommerce_app.dao.ProductRepository;
import in.sankarvinoth.ecommerce_app.exceptions.ProductExistsException;
import in.sankarvinoth.ecommerce_app.model.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	Message message;
	/**
	 * method to add the product to db 
	 * @param product
	 */
	public void addProductService(Product product) {
		List<Product> products=productRepository.findProductByProductId(product.getProductId());
		if(products.isEmpty()) {
			productRepository.saveProduct(product);
			message.setInfoMessage("Successfully Added to the stock");
			
		}
		else {
			throw new ProductExistsException("Product with this ProductId already exists");
		}
	}
	
	
	
}
