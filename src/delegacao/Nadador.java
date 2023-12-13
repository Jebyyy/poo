package delegacao;

public class Nadador extends Atleta {
	
	private String estilo;

	public Nadador(String nome, int numero, String estilo) {
		super(nome, numero);
		this.estilo = estilo;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	
	public String toString() {
		String retorno = super.toString();
		retorno += "Estilo: " + this.estilo + "\n";
		return retorno;
		
	}
}
