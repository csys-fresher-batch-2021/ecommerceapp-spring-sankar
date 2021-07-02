<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>E-commerceApp</title>
</head>

<jsp:include page="header.jsp"></jsp:include>
<body>

<form onsubmit="updateProductDetails()" method="post">

<h3>Edit Product </h3>
 ProductId:<input type="text" placeholder="Enter productId" id="productId"  title ="ProductId field should not be empty" " readonly required><br/>
 Product Name:<input type="text" placeholder="Enter productname" id="productName" required><br/>
 product category:<input type="text" placeholder="Enter product category"  pattern="^[A-Za-z]{4,20}$"  title="ProductCategory should be in alphabets" id="category"      required><br/>
 productPrice:<input type="number" placeholder="Enter product price"  min="1" max="100000" id="price"  pattern="^[0-10000000]{7}$"  title="ProductPrice should be in numbers and not greater than 10 lakhs "     required><br/>
 product quantity:<input type="number" placeholder="Enter productQuantity" id="quantity"  min="1" max="500"  pattern="^[0-500]{4}$" title="ProductQuantity should be in numbers and not greater than 500 and not less than 1"  required><br/>
   Product available status<select id="productStatus">
  <option value="available">available</option>
  <option value="not available">not available</option>
</select ><br/>
 <button type="submit"  class="btn btn-success" >Save</button>
 </form>
 <script >
 
 function editProduct() {
		let productsToEdit = localStorage.getItem("Product_to_Edit");
		let  products =  productsToEdit!= null ? JSON.parse(productsToEdit) : [];
		
		for(let product of products) {
			document.getElementById("productId").value=product.productId;
			document.getElementById("productName").value=product.productName;
			document.getElementById("category").value=product.category;
			document.getElementById("price").value=product.price;
			document.getElementById("quantity").value=product.quantity;
		}
}
 editProduct();
 
 function updateProductDetails() {
		event.preventDefault();
		let productName = document.getElementById("productName").value;
		let productId = document.getElementById("productId").value;
		let category = document.getElementById("category").value;
		let price= document.getElementById("price").value;
		let quantity = document.getElementById("quantity").value;
		let availableStatus= document.querySelector("#productStatus").value;
		
		let data={
				"productName":productName,
				"productId":productId,
				"category":category,
				"price":price,
				"quantity":quantity,
				"status":availableStatus
			       
			};
		
		console.log(data);				
		let url = "UpdateProduct";	
		axios.put(url,data).then(res=>{
			console.log(res.data);
			toastr.success("product updated Successfully");
			setTimeout(function(){ window.location="ListProducts.jsp"; }, 1000);
			localStorage.removeItem("Product_to_Edit");
			
		});
	}
 
 
</script>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js" integrity="sha512-bZS47S7sPOxkjU/4Bt0zrhEtWx0y0CRkhEp8IckzK+ltifIIE9EMIMTuT/mEzoIMewUINruDBIR/jJnbguonqQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</html>