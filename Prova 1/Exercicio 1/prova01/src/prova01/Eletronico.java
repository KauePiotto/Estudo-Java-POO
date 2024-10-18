package prova01;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Eletronico extends Produto implements CadastroProduto {
	private String NumeroDeSerie;
	private String Marca;
	private String Modelo;
	private String Condicao;
	private Garantia garantia;

	static ArrayList<Eletronico> eletroCadastrados = new ArrayList<>();

	public Eletronico() {

	}

	public Eletronico(String codigo, String nome, double preco, String numeroDeSerie, String marca, String modelo,
			String condicao, Garantia garantia) {
		super(codigo, nome, preco);
		this.NumeroDeSerie = numeroDeSerie;
		this.preco = preco;
		this.Marca = marca;
		this.Modelo = modelo;
		this.Condicao = condicao;
		this.garantia = garantia;
	}

	public String getNumeroDeSerie() {
		return NumeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		NumeroDeSerie = numeroDeSerie;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public String getCondicao() {
		return Condicao;
	}

	public void setCondicao(String condicao) {
		Condicao = condicao;
	}

	public Garantia getGarantia() {
		return garantia;
	}

	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}

	@Override
	public void CadastrarProduto() {
		eletroCadastrados.add(this);
		JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso: " + this);
	}

	@Override
	public void AlterarProduto() {
		JOptionPane.showInputDialog("Alterar Produto: ");
		this.Modelo = JOptionPane.showInputDialog("Digite o modelo ");
		this.Marca = JOptionPane.showInputDialog("Digite a marca ");
		this.Condicao = JOptionPane.showInputDialog("Digite a condição ");
		this.preco = Integer.parseInt(JOptionPane.showInputDialog("Digite o preço "));
		JOptionPane.showMessageDialog(null, "Produto alterado com sucesso" + this);
	}

	@Override
	public String toString() {
		return "Eletronico [NumeroDeSerie=" + NumeroDeSerie + ", Preço=" + preco + ", Marca=" + Marca + ", Modelo="
				+ Modelo + ", Condicao=" + Condicao + ", garantia=" + garantia + ", eletroCadastrados="
				+ eletroCadastrados + "]";
	}
}
