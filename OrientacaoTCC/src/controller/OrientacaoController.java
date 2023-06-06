package controller;

import model.Orientacao;
import model.pilha.Pilha;

public class OrientacaoController {
	
	private Pilha orientacoes = new Pilha();
	private int codOrientacao = 0;
	
	public OrientacaoController() {
	}

	public Orientacao buscarUltimaOrientacao() {
		try {
			return orientacoes.top();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void cadastro(Orientacao orientacao) {
		orientacao.setCodigo(gerarCodOrientacao());
		orientacoes.push(orientacao);
	}
	
	private int gerarCodOrientacao() {
		this.codOrientacao++;
		return codOrientacao;
	}

}
