<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>E-CommerceApp</title>
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js" integrity="sha512-bZS47S7sPOxkjU/4Bt0zrhEtWx0y0CRkhEp8IckzK+ltifIIE9EMIMTuT/mEzoIMewUINruDBIR/jJnbguonqQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>User Cart</h3>
		<table class="table table-bordered" >
			<caption>Products name along with its serial number</caption>
			<thead>
				    <tr>
					<th scope="col">S.no</th>
					<th scope="col">Product Name</th>
					<th scope="col">Product category</th>
					<th scope="col">Product Price(in Rs.)</th>
					<th scope="col">Product quantity</th>
					<th scope="col">Product Total</th>
					
					<th scope="col">Remove</th>
				</tr>
			</thead>
			<tbody id="carttable">
			</tbody>
		</table>
		<a href="EmptyCartServlet">Empty cart</a>
	Sub Total(in Rs.) :
	<input type="number" name="TotalAmount" id="total" readonly><br/>
	
	Total Price With 10 % Gst(in Rs.):
	<input type="number" name="TotalAmountwithgst" id="totalwithgst"
		readonly><br/>
		
	<button type="button" class="btn btn-info" onclick="placeOrder()">Confirm
		Order</button>
		
<script>
function loadProducts(){
let url="getAllCartItems";
	axios.get(url).then(res=>  {
			console.log(res.data);
			let products=res.data;
			let i=0;
			let content="";
			for(let product of products) {
				let quantity= "<input type='number'  onchange=productTotal('"+product.productId+"') id='quantity_"+product.productId+"' min='1' max='100'>";
				let removeButton="<button type='button' onclick=removeFromCart('"+product.productId+"') class='btn btn-danger'>Remove</button>";
				let price="<input type='number' id='amount_"+product.productId+"'  value='"+product.price+"' readonly>";
				let productTotal="<input type='number'   id='producttotal_"+product.productId+"' readonly>";
				content+= "<tr><td>"+(++i)+"</td><td>"+product.productName+"</td><td>"+product.category+"</td><td>"+price+"</td><td>"+quantity+"</td><td>"+productTotal+"</td><td>"+removeButton+"</td></tr>";
				
			}
			console.log(content);
			document.querySelector("#carttable").innerHTML =content;
			
		});
	}
	loadProducts();
	function productTotal(productId) {
		let total = 0;
		let price=document.getElementById("amount_"+productId).value;
		let qty=document.getElementById("quantity_"+productId).value;
		let amount = price*qty;
		document.getElementById("producttotal_"+productId).value=amount;
		
		let obj = {productId:productId, qty: qty, amount : amount,totalCartAmount:totalwithgst,totalAmountWithOutGST:total};
		let cartItemStr = localStorage.getItem("CART_ITEMS");
		let items = cartItemStr != null ? JSON.parse(cartItemStr) : [];
		//if productName already exists, remove old record by index.
		let index = items.findIndex(obj=> obj.productId=== productId);
		if(index != -1){
			items.splice(index,1);
		}
		items.push(obj);
		localStorage.setItem("CART_ITEMS", JSON.stringify(items));
		
		for(let item of items){
			total+= item.amount;
		}
		document.getElementById("total").value=total;
		let amountWithGst=((total*10)/100)+total;
		document.getElementById("totalwithgst").value=amountWithGst;
		
	}
	
	function emptyCart(){
		localStorage.removeItem("CART_ITEMS");
	}
	emptyCart();
	
	function placeOrder() {
		//let userName= (String)session.getAttribute("Logged_in_User");
		let totalAmounts=document.getElementById("total").value;
		let totalCartAmount=document.getElementById("totalwithgst").value;
		let username=document.getElementById("username").value;
		let url="http://localhost:9000/AddOrderServlet";
		let cartItemStr = localStorage.getItem("CART_ITEMS");
		let items = cartItemStr != null ? JSON.parse(cartItemStr) : [];
		let data={userId:username,items:items,totalAmount:totalCartAmount,totalAmountWithOutGST:totalAmounts};
		axios.post(url,data).then(res=> {
			console.log(res);
			if(totalAmounts==0) {
				alert("nothing in your cart");
			}else {
			alert("order placed successfully");
			window.location = "index.jsp";
		}
		});
	}
	function removeFromCart(productId) {
		let url="RemoveFromCart/"+productId;
		axios.delete(url).then(res=> {
			console.log(res.data);
			alert("removed from cart Successfully");
			removeItemFromLocalStorage(productId);
			loadProducts();
			window.location="Cart.jsp";
			
		});
	}
	
	function removeItemFromLocalStorage(productId){
		let cartItemsStr = localStorage.getItem("ITEMS_ADDED");
		const cartItems = cartItemsStr == null ? []: JSON.parse(cartItemsStr);
		let index = cartItems.findIndex(obj=> obj.productId == productId);
		if(index !=-1){
			cartItems.splice(index,1);
			localStorage.setItem("ITEMS_ADDED", JSON.stringify(cartItems));
		}
	}
	
	//localStorage.removeItem("ITEMS_ADDED");
	

	</script>
</main>
</body>
</html>