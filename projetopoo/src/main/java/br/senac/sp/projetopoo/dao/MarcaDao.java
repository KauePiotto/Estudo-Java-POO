package br.senac.sp.projetopoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.senac.sp.projetopoo.modelo.Marca;

public class MarcaDao {
	private Connection conexao;
	private String sql;
	private PreparedStatement stmt;

	public MarcaDao(Connection conexao) {
		this.conexao = conexao;
	}

	public void inserir(Marca marca) throws SQLException {
		sql = "insert into marca(nome, logo) values(?,?)";
		stmt = conexao.prepareStatement(sql);
		stmt.setString(1, marca.getNome());
		stmt.setBytes(2, marca.getLogo());
		stmt.execute();
		stmt.close();
	}

	public List<Marca> listar() throws SQLException {
		List<Marca> lista = new ArrayList<Marca>();
		sql = "select * from marca";
		stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Marca m = new Marca();
			m.setId(rs.getInt("id"));
			m.setNome(rs.getString("Nome"));
			m.setLogo(rs.getBytes("logo"));
		}
		rs.close();
		stmt.close();
		
		return lista;
	}
}