package prova01;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

public class Garantia implements CadastroProduto {
	private String DataDeVencimento;
	private String Status;

	public Garantia() {

	}

	public Garantia(String dataDeVencimento, String status) {
		DataDeVencimento = dataDeVencimento;
		Status = status;
	}

	public String getDataDeVencimento() {
		return DataDeVencimento;
	}

	public void setDataDeVencimento(String dataDeVencimento) {
		DataDeVencimento = dataDeVencimento;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void validarValidade() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			LocalDate dataVencimento = LocalDate.parse(DataDeVencimento, formatter);
			LocalDate dataAtual = LocalDate.now();
			if (dataVencimento.isBefore(dataAtual)) {
				Status = "Vencida";
			} else {
				Status = "Válida";
			}
		} catch (DateTimeParseException e) {
			Status = "Data Inválida";
		}
	}

	@Override
	public void CadastrarProduto() {
		JOptionPane.showMessageDialog(null, "Garantia cadastrada com sucesso: " + this);
	}

	@Override
	public void AlterarProduto() {
		this.DataDeVencimento = "31/12/2026";
		JOptionPane.showMessageDialog(null, "Garantia alterada com sucesso" + this);
	}

	@Override
	public String toString() {
		return "Garantia [DataDeVencimento=" + DataDeVencimento + ", Status=" + Status + "]";
	}
}
