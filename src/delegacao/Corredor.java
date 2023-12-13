package delegacao;

public class Corredor extends Atleta {

	private double velocidade;
	
	public Corredor(String nome, int numero, double velocidade) {
		super(nome, numero);
		this.velocidade = velocidade;
	}

	public double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}
	
	public String toString() {
		String retorno = super.toString();
		retorno += "Velocidade: " + this.velocidade + "\n";
		return retorno;
		
	}
	
}
