package model;

public class SubArea {
	public int codSubArea;
	public String nomeSubArea;
	
	public int getCodSubArea() {
		return codSubArea;
	}
	public void setCodSubArea(int codSubArea) {
		this.codSubArea = codSubArea;
	}
	public String getNomeSubArea() {
		return nomeSubArea;
	}
	public void setNomeSubArea(String nomeSubArea) {
		this.nomeSubArea = nomeSubArea;
	}
	
	@Override
	public String toString() {
		return "SubArea [codSubArea=" + codSubArea + ", nomeSubArea=" + nomeSubArea + "]";
	}
}
