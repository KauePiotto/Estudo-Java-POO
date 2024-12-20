package prova01;

import javax.swing.JOptionPane;

public class TesteProduto {
	public static void main(String[] args) {
		int opcao;
		do {
			opcao = Integer.parseInt(JOptionPane
					.showInputDialog("Escolha uma opção:\n" +"1 - Cadastrar Eletrônico\n"+"2 - Cadastrar Smarphone\n"+ "3 - Alterar Eletrônico\n" + "5 - Sair"));

			switch (opcao) {
			case 1:
				CadastrarEletronico();
				break;
			case 2:
				AlterarEletronico();
				break;
			case 3:
				CadastrarSmartphone();
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Saindo...");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida, escolha uma das opções acima.");
			}
		} while (opcao != 5);
	}

	private static void CadastrarEletronico() {
		String Codigo, Nome, NumeroDeSerie, Marca, Modelo, Condicao;
		double Preco;

		JOptionPane.showMessageDialog(null, "Cadastro de Eletrônico:");

		Codigo = JOptionPane.showInputDialog("Código: ");
		Nome = JOptionPane.showInputDialog("Nome: ");
		Preco = Double.parseDouble(JOptionPane.showInputDialog("Preço: "));
		NumeroDeSerie = JOptionPane.showInputDialog("Numero De Serie: ");
		Marca = JOptionPane.showInputDialog("Marca: ");
		Modelo = JOptionPane.showInputDialog("Modelo");
		Condicao = JOptionPane.showInputDialog("Condição");

		Garantia garantia = new Garantia("15/12/2025", "Válida");
		Eletronico eletronico = new Eletronico(Codigo, Nome, Preco, NumeroDeSerie, Marca, Modelo, Condicao, garantia);
		eletronico.CadastrarProduto();
	}

	private static void AlterarEletronico() {
		if (!Eletronico.eletroCadastrados.isEmpty()) {
			Eletronico eletronico = Eletronico.eletroCadastrados.get(0); // Exemplo simplificado
			eletronico.AlterarProduto();
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum eletrônico cadastrado.");
		}
	}

	private static void CadastrarSmartphone() {
		String codigo = JOptionPane.showInputDialog("Código:");
		String nome = JOptionPane.showInputDialog("Nome:");
		double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço:"));
		String memoria = JOptionPane.showInputDialog("Memória:");
		String corStr = JOptionPane.showInputDialog("Cor (0: Preto, 1: Branco, 2: Prata, 3: Azul, 4: Vermelho):");
		Cor cor = Cor.values()[Integer.parseInt(corStr)];

		Smartphone smartphone = new Smartphone(codigo, nome, preco, memoria, cor);
		smartphone.CadastrarProduto();
	}
}