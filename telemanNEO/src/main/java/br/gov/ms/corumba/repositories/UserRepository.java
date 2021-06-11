package br.gov.ms.corumba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.ms.corumba.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String username);

}
