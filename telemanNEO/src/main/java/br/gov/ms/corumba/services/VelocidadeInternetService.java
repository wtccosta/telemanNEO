package br.gov.ms.corumba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ms.corumba.entities.VelocidadeInternet;
import br.gov.ms.corumba.repositories.VelocidadeInternetRepository;
import br.gov.ms.corumba.services.exceptions.ResourceNotFoundException;

@Service
public class VelocidadeInternetService {

	@Autowired
	private VelocidadeInternetRepository repository;
	
	@Transactional(readOnly = true)
	public VelocidadeInternet findOneEntity(Long id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Velocidade de internet não cadastrada, id: "+id));
		
	}
}
