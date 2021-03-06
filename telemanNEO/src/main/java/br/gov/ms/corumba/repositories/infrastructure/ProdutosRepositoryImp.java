package br.gov.ms.corumba.repositories.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ms.corumba.entities.Produto;
import br.gov.ms.corumba.repositories.ProdutosRepositoryQueries;

@Repository
public class ProdutosRepositoryImp implements ProdutosRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional
	public void deleteOneProduct(Produto id) {
		manager.remove(manager.find(Produto.class, id));
	}

}
