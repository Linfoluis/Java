function addCart(id){
	location = `./carrinho/add?id=${id}`
}

function removeCart(id){
	location = `./carrinho/remove?id=${id}`
}	 

function checkoutCart() {
	location = `./carrinho/finalizar`
}