package br.senac.sp.projetopoo.dao;

import java.util.List;
import br.senac.sp.projetopoo.modelo.Marca;
import br.senac.sp.projetopoo.modelo.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class MarcaDaoHib implements InterfaceDao<Marca> {
    private EntityManager manager;

    public MarcaDaoHib(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void inserir(Marca objeto) throws Exception {
        this.manager.getTransaction().begin(); // Inicia a transação antes
        this.manager.persist(objeto);
        this.manager.getTransaction().commit();
    }

    @Override
    public void alterar(Marca objeto) throws Exception {
        this.manager.getTransaction().begin();
        this.manager.merge(objeto);
        this.manager.getTransaction().commit();
    }

    @Override
    public Marca buscar(int id) throws Exception {
        return this.manager.find(Marca.class, id);
    }

    @Override
    public void excluir(int id) throws Exception {
        Marca marca = buscar(id);
        if (marca != null) {
            this.manager.getTransaction().begin();
            this.manager.remove(marca);
            this.manager.getTransaction().commit();
        }
    }

    @Override
    public List<Marca> listar() throws Exception {
        TypedQuery<Marca> query = this.manager.createQuery("select m from Marca m order by m.nome", Marca.class);
        return query.getResultList();
    }

    @Override
    public void excluir(long id) throws Exception {
        throw new UnsupportedOperationException("Operação não implementada.");
    }

    @Override
    public List<Marca> listarMarcas() {
        throw new UnsupportedOperationException("Operação não implementada.");
    }

    public void fechar() {
        if (this.manager.isOpen()) {
            this.manager.close();
        }
    }

	@Override
	public List<Produto> buscar(String nome) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}