package br.gov.ms.corumba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.ms.corumba.entities.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> {

}
