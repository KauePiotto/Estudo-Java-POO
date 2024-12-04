package br.senac.sp.projetopoo.dao;

import java.util.List;

import br.senac.sp.projetopoo.modelo.Marca;
import br.senac.sp.projetopoo.modelo.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ProdutoDaoHib implements InterfaceDao<Produto> {
	private EntityManager manager;

	public ProdutoDaoHib(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void inserir(Produto objeto) throws Exception {
		this.manager.persist(objeto);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
	}

	@Override
	public void alterar(Produto objeto) throws Exception {
		this.manager.merge(objeto);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();

	}

	@Override
	public Produto buscar(int id) throws Exception {
		return this.manager.find(Produto.class, id);
	}

	@Override
	public void excluir(long id) throws Exception {
		this.manager.getTransaction().begin(); 
		Produto produto = buscar((int) id);
		this.manager.remove(produto);
		this.manager.getTransaction().commit();
	}

	public List<Produto> listar() throws Exception {
		TypedQuery<Produto> query = this.manager.createQuery("select m from Produto m order by m.nome", Produto.class);
		return query.getResultList();
	}

	@Override
	public void excluir(int id) throws Exception {
		// TODO Auto-generated method stub
	}

	public List<Marca> listarMarcas() {
		try {
			TypedQuery<Marca> query = manager.createQuery("SELECT m FROM Marca m ORDER BY m.nome", Marca.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Marca buscarMarcaPorNome(String nome) throws Exception {
		try {
			TypedQuery<Marca> query = manager.createQuery("SELECT m FROM Marca m WHERE m.nome = :nome", Marca.class);
			query.setParameter("nome", nome);
			return query.getSingleResult(); 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Produto> buscarPorMarca(String nomeMarca) throws Exception {
		try {
			TypedQuery<Produto> query = manager.createQuery("SELECT p FROM Produto p WHERE p.marca.nome = :nomeMarca",
					Produto.class);
			query.setParameter("nomeMarca", nomeMarca);
			return query.getResultList(); 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}