package br.gov.ms.corumba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ms.corumba.entities.Endereco;
import br.gov.ms.corumba.repositories.EnderecoRepository;
import br.gov.ms.corumba.services.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;
	
	@Transactional(readOnly = true)
	public Endereco findOnentity(Long id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Endereço não encontrado, id: "+id));
		
	}
}
