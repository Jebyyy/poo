package delegacao;

public class Saltador extends Atleta{
	
	private int altura;

	public Saltador(String nome, int numero, int altura) {
		super(nome, numero);
		this.altura = altura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	public String toString() {
		String retorno = super.toString();
		retorno += "Altura: " + this.altura + "\n";
		return retorno;
		
	}

}
