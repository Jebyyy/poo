package delegacao;

import java.io.Serializable;

public abstract class Atleta implements Serializable {
	
	private String nome;
	private int numero;

	public Atleta(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
	}
	
	public String toString() {
		String retorno = "";
		retorno += "Nome: " + this.nome + "\n";
		retorno += "Número: " + this.numero + "\n";
		return retorno;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
