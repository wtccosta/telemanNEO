package br.gov.ms.corumba.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.ms.corumba.entities.Produto;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Long> {

	Optional<Produto> findByLinhaOuRamal(String linhaOuRamal); 
}
