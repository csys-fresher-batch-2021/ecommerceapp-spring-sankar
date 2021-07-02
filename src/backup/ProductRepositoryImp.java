package in.sankarvinoth.ecommerce_app.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import in.sankarvinoth.ecommerce_app.model.Product;
/**
 * 
 * @author sank2640
 *
 */
@Repository
public class ProductRepositoryImp implements ProductRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final String SQL1="insert into productInfo(productId,productName,category,price,quantity,status,quantitiesLeft) values(?,?,?,?,?,?,?)";
	private static final String SQL2= "SELECT productId,productName,category,price,quantity,status FROM productinfo WHERE status = ? order by productName";
	private static final String SQL3= "SELECT productId,productName,category,price FROM productinfo WHERE productId=?";
	  private static final List<Product> cartItems=new ArrayList<>();      
	/**
	 * method to insert product to the database
	 */
	  public int saveProduct(Product product) {
		int rowsAffected=jdbcTemplate.update(SQL1,product.getProductId(),product.getProductName(),product.getCategory(),product.getPrice(),product.getQuantity(),product.getStatus(),product.getQuantity());
		return rowsAffected;
	 }
	  /**
	   * method to get all the available products from the database
	   */
	
	public List<Product>  getAllProducts(String status) {
         return jdbcTemplate.query(SQL2, BeanPropertyRowMapper.newInstance(Product.class),status);
         }
	/**
	 * method to get the product details matches with the productid
	 */
	
	public void getAllCartItems(String productId) {
           List<Product> cart= jdbcTemplate.query(SQL3, BeanPropertyRowMapper.newInstance(Product.class),productId);
           cartItems.addAll(cart);
   }
	
	/**
	 * method to get all the cart items
	 */
   public List<Product> getAllPro() {
	   System.out.println(cartItems);
	   return cartItems;
   }
   /**
    * method to remove product from the cart 
    */
   public void removeProductFromCart(String productId) {
	 for(Product product:cartItems) {
		 if(productId.equalsIgnoreCase(product.getProductId())) {
			 cartItems.remove(product);
			 break;
		 }
	 }
	   
   }
	
	

}
