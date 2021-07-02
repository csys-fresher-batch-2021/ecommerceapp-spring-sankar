package in.sankarvinoth.ecommerce_app.dao;

import java.util.List;

import in.sankarvinoth.ecommerce_app.model.Product;
/**
 * 
 * @author sank2640
 *
 */
public interface ProductRepository {
	public int saveProduct(Product product);
	public List<Product>  getAllProducts(String status);
	public void getAllCartItems(String productId);
	public List<Product> getAllPro();
	public void removeProductFromCart(String productId);

}
