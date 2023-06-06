package model;

import java.util.Date;

public class Orientacao {
	public int codigo;
	public String data;
	public String observação;
	public int codGrupo;
	public int codProfessor;
	
	public Orientacao() {
	}
	
	public Orientacao(String observacao, int codGrupo, int codProfessor, String data) {
		this.observação = observacao;
		this.codGrupo = codGrupo;
		this.codProfessor = codProfessor;
		this.data = data;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getObservação() {
		return observação;
	}
	public void setObservação(String observação) {
		this.observação = observação;
	}
	public int getCodGrupo() {
		return codGrupo;
	}
	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}
	public int getCodProfessor() {
		return codProfessor;
	}
	public void setCodProfessor(int codProfessor) {
		this.codProfessor = codProfessor;
	}
	
	@Override
	public String toString() {
		return "Orientação [codigo=" + codigo + ", data=" + data + ", observação=" + observação + ", codGrupo=" + codGrupo
				+ ", codProfessor=" + codProfessor + "]";
	}
}
