package Entity;

public class Aluno {
	private int ra;
	private String nome;
	
	public int getRa() {
		return ra;
	}
	public void setRa(int ra) {
		this.ra = ra;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String toString() {
		return this.ra + " - " + this.nome;
	}
	
}
