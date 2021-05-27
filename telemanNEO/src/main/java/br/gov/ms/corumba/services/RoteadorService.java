package br.gov.ms.corumba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ms.corumba.entities.Roteador;
import br.gov.ms.corumba.repositories.RoteadorRepository;
import br.gov.ms.corumba.services.exceptions.ResourceNotFoundException;

@Service
public class RoteadorService {

	@Autowired
	private RoteadorRepository repository;
	
	@Transactional(readOnly = true)
	public Roteador findOneEntity(Long id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Roteador não encontrado, id: "+id));
		
	}
}
