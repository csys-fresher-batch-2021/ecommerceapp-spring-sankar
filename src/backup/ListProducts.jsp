<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>E-CommerceApp</title>
</head>
<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js" integrity="sha512-bZS47S7sPOxkjU/4Bt0zrhEtWx0y0CRkhEp8IckzK+ltifIIE9EMIMTuT/mEzoIMewUINruDBIR/jJnbguonqQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Products available in our shops</h3>
		<table class="table table-bordered">
		<caption>Products name along with  its serial number </caption>
			<thead>
				<tr>
					<th scope="col">S.no</th>
					<th scope="col">Product Id</th>
					<th scope="col">Product Name</th>
					<th scope="col">Product category</th>
					<th scope ="col">Product Price(in Rs.)</th>
					<th scope ="col">quantities available</th>
					<th scope ="col">Service Status</th>
					<th scope ="col">Order Here</th>
					<th scope ="col">Actions</th>
					 </tr>
					 </thead>
					 <tbody id="producttable">
                     </tbody>
                      </table>
                      <a href="Cart.jsp">View Cart</a>
                      <script>
function loadProducts() {
	let url="GetAllProducts";
		axios.get(url).then(res=>  {
		console.log(res.data);
		let products=res.data;
		let i=0;
		
		let cartItemsStr = localStorage.getItem("ITEMS_ADDED");
		const cartItems = cartItemsStr == null ? []: JSON.parse(cartItemsStr);
		let content="";
		for(let product of products) {
			let isAdded = cartItems.some(obj=> obj.productId == product.productId);
			
			let cartButton =""
			if(isAdded){
				cartButton ="Added";
			}
			else{
				cartButton = "<button type='button' id='btn' onclick=addToCart('"+product.productId+"') class='btn btn-success'>Add to Cart</button>";
			}
			
			let editProductButton ="<button type='button' class='btn btn-info'>Edit Product</button>";
			content+= "<tr><td>"+(++i)+"</td><td>"+product.productId+"</td><td>"+product.productName+"</td><td>"+product.category+"</td><td>"+product.price+"</td><td>"+product.quantity+"</td><td>"+product.status+"</td><td>"+cartButton+"</td><td>"+editProductButton+"</td></tr>";
		}
		console.log(content);
		document.querySelector("#producttable").innerHTML =content;
		
	});
}
loadProducts();
function addToCart(productId) {
	let url="AddToCart/"+productId;
	axios.post(url).then(res=> {
		console.log(res.data);
		alert("Added to cart Successfully");
		let cartItemsStr = localStorage.getItem("ITEMS_ADDED");
		const cartItems = cartItemsStr == null ? []: JSON.parse(cartItemsStr);
		let item = { productId: productId, quantity: 1};
		cartItems.push(item);
		localStorage.setItem("ITEMS_ADDED", JSON.stringify(cartItems));
		loadProducts();
		
	});
}
</script>
</main>
</body>

</html>