package model;

import java.util.List;

public class Grupo {
	private int codigo;
	private String temaTCC;
	private int codSubarea;
	private List<Aluno> alunos;
	
	public Grupo() {
	}
	
	public Grupo(String temaTcc, int codSubArea) {
		this.temaTCC = temaTcc;
		this.codSubarea = codSubArea;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTemaTCC() {
		return temaTCC;
	}
	public void setTemaTCC(String temaTCC) {
		this.temaTCC = temaTCC;
	}
	public int getCodSubarea() {
		return codSubarea;
	}
	public void setCodSubarea(int codSubarea) {
		this.codSubarea = codSubarea;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	@Override
	public String toString() {
		return "Grupo [codigo=" + codigo + ", temaTCC=" + temaTCC + ", subarea=" + codSubarea + ", alunos=" + alunos + "]";
	}
	
}
