package model.pilha;

import model.Orientacao;

public class Pilha {
	No topo;
	
	public Pilha() {
		topo = null;
	}
	
	public boolean isEmpty() {
		if (topo == null) return true;
		else return false;
	}
	
	public void push(Orientacao e) {
		No elemento = new No();
		elemento.dado = e;
		
		if (isEmpty()) {
			topo = elemento;
		} else {
			elemento.proximo = topo;
			topo = elemento;
		}
	}
	
	public Orientacao pop() throws Exception {
		if (isEmpty())
			throw new Exception("N�o h� elementos para desempilhar");
		
		Orientacao dado = topo.dado;
		topo = topo.proximo;
		return dado;
	}
	
	public Orientacao top() throws Exception {
		if (isEmpty()) {
			throw new Exception("N�o h� elementos na pilha");
		}
		return topo.dado;
	}
	
	public int size() {
		int count = 0;
		
		if (!isEmpty()) {
			No auxiliar = topo;
			count = 1;
			while(auxiliar.proximo != null) {
				auxiliar = auxiliar.proximo;
				count++;
			}
		}
		return count;
	}
}
