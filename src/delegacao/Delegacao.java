package delegacao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Delegacao {
	private ArrayList<Atleta> atletas;

	public Delegacao() {
		this.atletas = new ArrayList<Atleta>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}
	
	public Nadador leNadador (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Número", "Estilo"};
		valores = leValores (nomeVal);

		int numero = this.retornaInteiro(valores[1]);
		String estilo = this.retornaString(valores[2]); //Estilos mais famosos crawl, costas, peito, borbolea e medley

		Nadador nadador = new Nadador (valores[0],numero,estilo);
		return nadador;
	}

	public Corredor leCorredor (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Número", "Velocidade"};
		valores = leValores (nomeVal);

		int numero = this.retornaInteiro(valores[1]);
		double velocidade = this.retornaInteiro(valores[2]);

		Corredor corredor = new Corredor (valores[0],numero,velocidade);
		return corredor;
	}
	
	public Saltador leSaltador (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Número", "Altura"};
		valores = leValores (nomeVal);

		int numero = this.retornaInteiro(valores[1]);
		int altura = this.retornaInteiro(valores[2]);

		Saltador saltador = new Saltador (valores[0],numero,altura);
		return saltador;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); 
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public int retornaInteiro(String entrada) {

		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\nDigite apenas números inteiros!");
		}
		return Integer.parseInt(entrada);
	}
	
	private boolean stringValida(String s) {
	    return s.matches("[a-zA-Z]+");
	}

	
	private String retornaString(String string) {
		
	    while (!this.stringValida(string)) {
	        string = JOptionPane.showInputDialog(null, "Valor incorreto!\nDigite um estilo!");
	    }
	    return string;
	}
		
	public void salvaAtletas (ArrayList<Atleta> atletas){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("delegação.dados"));
			for (int i=0; i < atletas.size(); i++)
				outputStream.writeObject(atletas.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossivel criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Atleta> recuperaAtletas (){
		ArrayList<Atleta> atletasTemp = new ArrayList<Atleta>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("delegação.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Atleta) {
					atletasTemp.add((Atleta) obj);
				}   
			}          
		} catch (EOFException ex) { 
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com atletas não existente!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally { 
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return atletasTemp;
		}
	}

	public void menuDelegacao (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Menu de Atletas\n" +
					"Opções:\n" + 
					"1. Entrar Atletas\n" +
					"2. Exibir Atletas\n" +
					"3. Limpar Atletas\n" +
					"4. Gravar Atletas\n" +
					"5. Recuperar Atletas\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Modalidades\n" +
						"Opções:\n" + 
						"1. Nadador\n" +
						"2. Corredor\n" +
						"3. Saltador\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: atletas.add((Atleta)leNadador());
				break;
				case 2: atletas.add((Atleta)leCorredor());
				break;
				case 3: atletas.add((Atleta)leSaltador());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Modalidade não escolhida!");
				}

				break;
			case 2: // Exibir dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Adicione um atleta primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < atletas.size(); i++)	{
					dados += atletas.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Adicione um atleta primeiramente");
					break;
				}
				atletas.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Adicione um atleta primeiramente");
					break;
				}
				salvaAtletas(atletas);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				atletas = recuperaAtletas();
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para serem exibidos.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo DELEGAÇÃO");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		Delegacao pet = new Delegacao ();
		pet.menuDelegacao();

	}

}

