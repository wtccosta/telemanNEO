package br.gov.ms.corumba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.ms.corumba.entities.Roteador;

@Repository
public interface RoteadorRepository extends JpaRepository<Roteador, Long> {

}
