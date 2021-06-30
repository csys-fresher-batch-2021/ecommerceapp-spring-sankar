package in.sankarvinoth.ecommerce_app.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import in.sankarvinoth.ecommerce_app.dao.ProductRepository;

import in.sankarvinoth.ecommerce_app.dto.ProductDTO;
import in.sankarvinoth.ecommerce_app.model.Product;
@RestController
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	Product product;
	/**
	 * method to insert the new Product into the database
	 * @param productdto
	 * @return
	 */
	@PostMapping("AddProducts")
	public int saveProduct(@RequestBody ProductDTO productdto) {
		product.setProductId(productdto.getProductId());
		product.setProductName(productdto.getProductName());
		product.setCategory(productdto.getCategory());
		product.setPrice(productdto.getPrice());
		product.setQuantity(productdto.getQuantity());
		product.setStatus(productdto.getStatus());
		 return productRepository.saveProduct(product);
	}
	/**
	 * method to display all the products
	 * @return
	 */
	@GetMapping("GetAllProducts")
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts("available");
	}
	/**
	 * method to add the selected products to the cart
	 * @param id
	 */
	@PostMapping("AddToCart/{productId}")
	public void getProductId(@PathVariable("productId") String id) {
		 productRepository.getAllCartItems(id);
	}
	/**
	 * method to display all the items in the cart
	 * @return
	 */
	@GetMapping("getAllCartItems")
	public List<Product> getAllCartItems() {
		return productRepository.getAllPro();
	}
	/**
	 * method to remove item from the cart
	 * @param productId
	 * @return
	 */
	
	@DeleteMapping("RemoveFromCart/{ProductId}")
   public String  removeCartItems(@PathVariable("ProductId")String productId) {
	 productRepository.removeProductFromCart(productId);
	 return "redirect:Cart.jsp";
}

}
