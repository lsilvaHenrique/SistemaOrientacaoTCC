package model;

public class Area {
	public int codigoArea;
	public String nomeArea;
	public SubArea subArea;
	public int getCodigoArea() {
		return codigoArea;
	}
	public void setCodigoArea(int codigoArea) {
		this.codigoArea = codigoArea;
	}
	public String getNomeArea() {
		return nomeArea;
	}
	public void setNomeArea(String nomeArea) {
		this.nomeArea = nomeArea;
	}
	public SubArea getSubArea() {
		return subArea;
	}
	public void setSubArea(SubArea subArea) {
		this.subArea = subArea;
	}
	@Override
	public String toString() {
		return "Area [codigoArea=" + codigoArea + ", nomeArea=" + nomeArea + ", subArea=" + subArea + "]";
	}
}
