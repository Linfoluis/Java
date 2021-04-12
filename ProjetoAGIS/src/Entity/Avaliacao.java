package Entity;

public class Avaliacao {
	private int CodDisciplina;
	private int CodAvalicao;
	private String tipo;
	private double peso;
	
	public int getCodDisciplina() {
		return CodDisciplina;
	}
	public void setCodDisciplina(int codDisciplina) {
		CodDisciplina = codDisciplina;
	}
	public int getCodAvalicao() {
		return CodAvalicao;
	}
	public void setCodAvalicao(int codAvalicao) {
		CodAvalicao = codAvalicao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return this.tipo;
	}
}
