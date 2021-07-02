package in.sankarvinoth.ecommerce_app.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import in.sankarvinoth.ecommerce_app.commons.APIResponse;
import in.sankarvinoth.ecommerce_app.commons.Message;
import in.sankarvinoth.ecommerce_app.dao.ProductRepository;
import in.sankarvinoth.ecommerce_app.dto.ProductDTO;
import in.sankarvinoth.ecommerce_app.model.Product;
import in.sankarvinoth.ecommerce_app.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	APIResponse apiResponse;
	@Autowired
	Message message;

	/**
	 * method to insert the new Product into the database
	 * 
	 * @param productdto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("AddProducts")
	public ResponseEntity saveProduct(@RequestBody ProductDTO productdto) {
		Product product=new Product();
		product.setProductId(productdto.getProductId());
		product.setProductName(productdto.getProductName());
		product.setCategory(productdto.getCategory());
		product.setPrice(productdto.getPrice());
		product.setQuantity(productdto.getQuantity());
		product.setStatus(productdto.getStatus());
		productService.addProductService(product);
		apiResponse.setData(productdto);
		apiResponse.setMessage(message.getInfoMessage());
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	/**
	 * method to display all the products
	 * 
	 * @return
	 */
	@GetMapping("GetAllProducts")
	public List<Product> getAllProducts() {
		return productRepository.findAllProducts("available");
	}

	/**
	 * method to add the selected products to the cart
	 * 
	 * @param id
	 */
	@PostMapping("AddToCart/{productId}")
	public void getProductId(@PathVariable("productId") String id) {
		productRepository.findProductByProductId(id);
	}

	/**
	 * method to display all the items in the cart
	 * 
	 * @return
	 */
	@GetMapping("getAllCartItems")
	public List<Product> getAllCartItems() {
		return productRepository.getAllProducts();
	}

	/**
	 * method to remove item from the cart
	 * 
	 * @param productId
	 * @return
	 */

	@DeleteMapping("RemoveFromCart/{ProductId}")
	public String removeCartItems(@PathVariable("ProductId") String productId) {
		productRepository.removeProductFromCart(productId);
		return "redirect:Cart.jsp";
	}
	/**
	 * method to get the product id of the product to be edited.
	 * @param id
	 * @return list which consists of product details which matches with the productId given
	 */
	@PostMapping("editProduct/{productId}")
	public List<Product> getProductById(@PathVariable("productId") String id) {
		return productRepository.findProductByProductId(id);
	}
	/**
	 * method to update the product details
	 * @param productdto
	 */
	
	@PutMapping("UpdateProduct") 
	public void updateProduct(@RequestBody ProductDTO productdto) {
		Product product=new Product();
		product.setProductId(productdto.getProductId());
		product.setProductName(productdto.getProductName());
		product.setPrice(productdto.getPrice());
		product.setCategory(productdto.getCategory());
		product.setQuantity(productdto.getQuantity());
		product.setStatus(productdto.getStatus());
		productRepository.updateProduct(product);
	}

}
