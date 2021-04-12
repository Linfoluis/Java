package Entity;

import java.sql.Date;
import java.time.LocalDate;

public class Falta {
	private int AlunoRA;
	private int disccodigo;
	private LocalDate data;
	private int presencas;
	private int nAulas;
	
	public void setData(LocalDate localDate) {
		this.data = localDate;
	}
	public int getAlunoRA() {
		return AlunoRA;
	}
	public void setAlunoRA(int alunoRA) {
		AlunoRA = alunoRA;
	}
	public int getDisccodigo() {
		return disccodigo;
	}
	public void setDisccodigo(int disccodigo) {
		this.disccodigo = disccodigo;
	}
	public LocalDate getData() {
		return data;
	}
	public int getPresencas() {
		return presencas;
	}
	public void setPresencas(int presencas) {
		this.presencas = presencas;
	}
	public int getnAulas() {
		return nAulas;
	}
	public void setnAulas(int nAulas) {
		this.nAulas = nAulas;
	}
	@Override
	public String toString() {
		return super.toString();
	}
}
