package br.com.caelum.lojasupersegura.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.lojasupersegura.models.Produto;

@Repository
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void adiciona(Produto produto) {
		entityManager.persist(produto);
	}

	public List<Produto> lista() {
		return entityManager.createQuery("select p from Produto p",Produto.class).getResultList();
	}

	public Produto carrega(Integer id) {
		return entityManager.find(Produto.class,id);
	}
	
	
}
