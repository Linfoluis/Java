package entity;

public class Item_Carrinho {

	private Produto produto;
	private int quantidade;
	
	public Item_Carrinho() {
		
	}
	
	public Item_Carrinho(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
			
	public double getPreco() {
		return produto.getPreco() * quantidade;
	}
}
