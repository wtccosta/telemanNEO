package br.gov.ms.corumba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ms.corumba.entities.Secretaria;
import br.gov.ms.corumba.repositories.SecretariaRepository;
import br.gov.ms.corumba.services.exceptions.ResourceNotFoundException;

@Service
public class SecretariaService {

	@Autowired
	private SecretariaRepository repository;
	
	@Transactional(readOnly = true)
	public Secretaria findOnentity(Long id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Secretaria não encontrado, id: "+id));
		
	}
}
