package br.senac.sp.projetopoo.tablemodel;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import br.senac.sp.projetopoo.modelo.Produto;

public class ProdutoTableModel extends AbstractTableModel {
	private List<Produto> lista;
	private String[] cabecalho = { "Id", "Logo", "Nome", "Marca", "Preço", "Descrição" };

	public ProdutoTableModel(List<Produto> produto) {
		this.lista = produto;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return cabecalho.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (lista == null || rowIndex < 0 || rowIndex >= lista.size()) {
			return null;
		}

		Produto p = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getId();
		case 1:
			if (p.getLogo() != null) {
				return new ImageIcon(p.getLogo());
			}
		case 2:
			return p.getNome();
		case 3:
			return p.getMarca().getNome();
		case 4:
			return String.format("R$ %.2f", p.getPreco());
		case 5:
			return p.getDescricao();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return cabecalho[column];
	}
}