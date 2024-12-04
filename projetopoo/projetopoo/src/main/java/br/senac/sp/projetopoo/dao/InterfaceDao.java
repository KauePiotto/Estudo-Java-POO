package br.senac.sp.projetopoo.dao;

import java.util.List;
import br.senac.sp.projetopoo.modelo.Marca;

public interface InterfaceDao <T> {
    void inserir(T objeto) throws Exception;
    void alterar(T objeto) throws Exception;
    T buscar(int id) throws Exception;
    void excluir(int id) throws Exception;
    void excluir(long id) throws Exception;
    List<T> listar() throws Exception;
    List<Marca> listarMarcas() throws Exception;
}