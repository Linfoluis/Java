package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Item_Carrinho> produtos = new ArrayList<Item_Carrinho>();
	
	public void add(Produto p, int quantidade) {
		Item_Carrinho item = new Item_Carrinho(p, quantidade);
		produtos.add(item);
	}
	
	public boolean remove(Item_Carrinho item) {
		return produtos.remove(item);
	}
	
	public double getTotal() {
		double total = 0.0;
		for(Item_Carrinho item : produtos) {
			total += item.getPreco();
		}
		return total;
	}
	
	public int getTamanho() {
		return produtos.size();
	}
	

}
