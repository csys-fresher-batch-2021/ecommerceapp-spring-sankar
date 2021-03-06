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
	public List<Product>  findAllProducts(String status);
	public List<Product> findProductByProductId(String productId);
	public List<Product> getAllProducts();
	public void removeProductFromCart(String productId);
	public void updateProduct(Product product);
}
