package Entity;

public class Nota {
	private int RA;
	private double nNota;
	private int CodDisc;
	private int AvalCod;
	private double nNota1;
	private double nNota2;
	private double nNota3;
	
	public double getnNota() {
		return nNota;
	}
	public void setnNota(double nNota) {
		this.nNota = nNota;
	}
	public double getnNota1() {
		return nNota1;
	}
	public void setnNota1(int nNota1) {
		this.nNota1 = nNota1;
	}
	public double getnNota2() {
		return nNota2;
	}
	public void setnNota2(double nNota2) {
		this.nNota2 = nNota2;
	}
	public double getnNota3() {
		return nNota3;
	}
	public void setnNota3(double nNota3) {
		this.nNota3 = nNota3;
	}
	public int getRA() {
		return RA;
	}
	public void setRA(int rA) {
		RA = rA;
	}
	public int getCodDisc() {
		return CodDisc;
	}
	public void setCodDisc(int codDisc) {
		CodDisc = codDisc;
	}
	public int getAvalCod() {
		return AvalCod;
	}
	public void setAvalCod(int avalCod) {
		AvalCod = avalCod;
	}
}
