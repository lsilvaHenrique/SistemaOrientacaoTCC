package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Aluno;
import model.Grupo;

public class GrupoController {
	
	private List<String[]> linhas = new ArrayList<>();
	private int codGrupo = 0;
	
	public GrupoController() {
	}

	public Grupo busca(int codigo) {
	    Grupo grupo = null;
	    List<Grupo> grupos = new ArrayList<>();

	    try (Scanner scanner = new Scanner(new File("grupos.csv"))) {
	        while (scanner.hasNextLine()) {
	            String linha = scanner.nextLine();
	            String[] campos = linha.split(",");

	            if (campos.length == 3) {
	                int codigoBase = Integer.parseInt(campos[0].trim());
	                if (codigoBase == codigo) {
	                    grupo = new Grupo();
	                    grupo.setCodigo(codigoBase);
	                    grupo.setTemaTCC(campos[1].trim());
	                    grupo.setCodSubarea(Integer.parseInt(campos[2].trim()));
	                    grupos.add(grupo);
	                }
	            }
	        }
	        
	        if (!grupos.isEmpty()) {
	            grupo = grupos.get(0); // Obtém o primeiro grupo encontrado
	            grupo.setAlunos(getAlunos(codigo));
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    
	    return grupo;
	}

	public Grupo buscaPorSubArea(int codSubArea) {
	    Grupo grupoEncontrado = new Grupo();

	    try (Scanner scanner = new Scanner(new File("grupos.csv"))) {
	        HashMap<Integer, Grupo> tabelaEspalhamento = new HashMap<>();

	        while (scanner.hasNextLine()) {
	            String linha = scanner.nextLine();
	            String[] campos = linha.split(",");

	            if (campos.length == 3) {
	                int codigoGrupo = Integer.parseInt(campos[0].trim());
	                String temaTCC = campos[1].trim();
	                int codigoSubArea = Integer.parseInt(campos[2].trim());

	                Grupo grupo = new Grupo(temaTCC, codigoSubArea);
	                grupo.setCodigo(codigoGrupo);

	                tabelaEspalhamento.put(codigoSubArea, grupo);
	            }
	        }

	        grupoEncontrado = tabelaEspalhamento.get(codSubArea);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }

	    return grupoEncontrado;
	}
	
	public void cadastro(Grupo grupo) {		
		try (FileWriter writer = new FileWriter("grupos.csv")) {
			linhas.add(new String[] { gerarCodGrupo(), grupo.getTemaTCC(), 
					convertIntegerToString(grupo.getCodSubarea()) });
			
			for (String[] linha : linhas) {
				String linhaCSV = String.join(",", linha);
		        writer.write(linhaCSV);
		        writer.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(grupo);
	}

	public int getCodGrupo() {
		return codGrupo;
	}
	
	private String gerarCodGrupo() {
		this.codGrupo++;
		return convertIntegerToString(codGrupo);
	}
	
	private String convertIntegerToString(int valor) {
		return Integer.toString(valor);
	}
	
	private List<Aluno> getAlunos(int codGrupo) {
		List<Aluno> alunos = new ArrayList<>();
		
		try (Scanner scanner = new Scanner(new File("alunos.csv"))) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(",");

                if (campos.length == 3) {
                	int codigoBase = Integer.parseInt(campos[2].trim());
                	if (codigoBase == codGrupo) {
                		Aluno aluno = new Aluno();
                		aluno.setRA(campos[0].trim());
                		aluno.setNome(campos[1].trim());
                		aluno.setCodGrupo(codGrupo);
                		
                		alunos.add(aluno);
                	}
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		return alunos;
	}
}
