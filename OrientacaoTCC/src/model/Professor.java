package model;

public class Professor {
	public int codigoProf;
	public String nomeProf;
	public Area area;
	
	public int getCodigoProf() {
		return codigoProf;
	}
	public void setCodigoProf(int codigoProf) {
		this.codigoProf = codigoProf;
	}
	public String getNomeProf() {
		return nomeProf;
	}
	public void setNomeProf(String nomeProf) {
		this.nomeProf = nomeProf;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return "Professor [codigoProf=" + codigoProf + ", nomeProf=" + nomeProf + ", area=" + area + "]";
	}
}
