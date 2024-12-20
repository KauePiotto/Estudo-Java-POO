package prova01;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Smartphone extends Produto implements CadastroProduto {
	public static Object smartphonesCadastrados;
	private String memoria;
	private Cor cor;

	 ArrayList<Smartphone> SmartCadastrado = new ArrayList<>();

	public Smartphone(String codigo, String nome, double preco, String memoria, Cor cor) {
		super(codigo, nome, preco);
		this.memoria = memoria;
		this.cor = cor;
	}

	public String getMemoria() {
		return memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	@Override
	public void CadastrarProduto() {
		SmartCadastrado.add(this);
		JOptionPane.showMessageDialog(null, "Smartphone cadastrado com sucesso: " + this);

	}

	@Override
	public void AlterarProduto() {
		this.memoria = "256GB";
		JOptionPane.showMessageDialog(null, "Smarphone alterado com sucesso" + this);
	}

	@Override
	public String toString() {
		return "Smartphone [memoria=" + memoria + ", cor=" + cor + ", SmartCadastrado=" + SmartCadastrado + "]";
	}

}
