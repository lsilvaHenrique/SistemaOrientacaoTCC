package controller;

import java.io.FileWriter;
import java.io.IOException;

import model.Aluno;

import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.List;

public class AlunoController {
	
	private List<String[]> linhas = new ArrayList<>();
	
	public AlunoController() {
	}

	public void cadastro(List<Aluno> alunos, int codGrupo) {		
		try (FileWriter writer = new FileWriter("alunos.csv")) {
			for (Aluno aluno : alunos) {
				aluno.setCodGrupo(codGrupo);
				linhas.add(new String[] {
					aluno.getRA(), aluno.getNome(), convertIntegerToString(aluno.getCodGrupo())
				});
				
				for (String[] linha : linhas) {
					String linhaCSV = String.join(",", linha);
			        writer.write(linhaCSV);
			        writer.write("\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(alunos);
	}
	
	private String convertIntegerToString(int valor) {
		return Integer.toString(valor);
	}
}
