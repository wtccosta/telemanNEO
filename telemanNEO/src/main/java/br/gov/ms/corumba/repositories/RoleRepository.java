package br.gov.ms.corumba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.ms.corumba.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
