package br.senac.sp.projetopoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.senac.sp.projetopoo.modelo.Marca;
import br.senac.sp.projetopoo.modelo.Produto;

public class MarcaDAO implements InterfaceDao<Marca> {
	private Connection conexao;
	private String sql;
	private PreparedStatement stmt;

	public MarcaDAO(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void inserir(Marca marca) throws SQLException {
		sql = "INSERT INTO marca(nome, logo) VALUES (?, ?)";
		stmt = conexao.prepareStatement(sql);
		stmt.setString(1, marca.getNome());
		stmt.setBytes(2, marca.getLogo());
		stmt.execute();
		stmt.close();
	}

	@Override
	public Marca buscar(int id) throws SQLException {
		sql = "SELECT * FROM marca WHERE id = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			Marca marca = new Marca();
			marca.setId(rs.getInt("id"));
			marca.setNome(rs.getString("nome"));
			marca.setLogo(rs.getBytes("logo"));
			rs.close();
			stmt.close();
			return marca;
		}
		rs.close();
		stmt.close();
		return null;
	}

	@Override
	public List<Marca> listar() throws SQLException {
		List<Marca> lista = new ArrayList<>();
		sql = "SELECT * FROM marca";
		stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Marca m = new Marca();
			m.setId(rs.getInt("id"));
			m.setNome(rs.getString("nome"));
			m.setLogo(rs.getBytes("logo"));
			lista.add(m);
		}
		rs.close();
		stmt.close();
		return lista;
	}

	@Override
	public void alterar(Marca marca) throws SQLException {
		sql = "UPDATE marca SET nome = ?, logo = ? WHERE id = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setString(1, marca.getNome());
		stmt.setBytes(2, marca.getLogo());
		stmt.setInt(3, marca.getId());
		stmt.execute();
		stmt.close();
	}

	@Override
	public void excluir(int id) throws SQLException {
		sql = "DELETE FROM marca WHERE id = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}

	@Override
	public void excluir(long id) throws Exception {
		throw new UnsupportedOperationException("Operação não implementada.");
	}

	@Override
	public List<Marca> listarMarcas() {
		throw new UnsupportedOperationException("Operação não implementada.");
	}

	@Override
	public List<Produto> buscar(String nome) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}