package Entity;

import javafx.scene.control.ComboBox;

public class Disciplina {
	private int codigoDisciplina;
	private String nomeDisciplina;
	private String siglaDisciplina;
	private String turnoDisciplina;
	private int nAulasDisciplina;
	
	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	public String getSiglaDisciplina() {
		return siglaDisciplina;
	}
	public void setSiglaDisciplina(String siglaDisciplina) {
		this.siglaDisciplina = siglaDisciplina;
	}
	public String getTurnoDisciplina() {
		return turnoDisciplina;
	}
	public void setTurnoDisciplina(String turnoDisciplina) {
		this.turnoDisciplina = turnoDisciplina;
	}
	public int getnAulasDisciplina() {
		return nAulasDisciplina;
	}
	public void setnAulasDisciplina(int i) {
		this.nAulasDisciplina = i;
	}
	public String toString() {
		return this.codigoDisciplina + " - " +this.nomeDisciplina + " - " + this.turnoDisciplina;
	}
}
