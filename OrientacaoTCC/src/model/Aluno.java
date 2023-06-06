package model;

public class Aluno {
	private String nome;
	private String RA;
	private int codGrupo;
	
	public Aluno() {
	}
	
	public Aluno(String nome, String ra) {
		this.nome = nome;
		this.RA = ra;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRA() {
		return RA;
	}
	public void setRA(String rA) {
		RA = rA;
	}
	public int getCodGrupo() {
		return codGrupo;
	}
	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}
	
	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", RA=" + RA + "]";
	}
}
