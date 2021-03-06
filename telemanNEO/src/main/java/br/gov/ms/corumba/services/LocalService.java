package br.gov.ms.corumba.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ms.corumba.entities.Local;
import br.gov.ms.corumba.repositories.LocalRepository;
import br.gov.ms.corumba.resources.dto.local.LocalDTO;
import br.gov.ms.corumba.services.exceptions.DatabaseException;
import br.gov.ms.corumba.services.exceptions.ResourceNotFoundException;

@Service
public class LocalService {

	@Autowired
	private LocalRepository repository;
	
	@Autowired
	private SecretariaService secretariaService;
	
	@Autowired
	private EnderecoService enderecoService;
	

	@Transactional(readOnly = true)
	public  Page<LocalDTO> findAllPaged(Pageable paged) {
		Page<Local> produtos = repository.findAll(paged);
	   return produtos.map(x -> new LocalDTO(x));
	}
	
	@Transactional(readOnly = true)
	public  List<LocalDTO> findAll() {
		List<Local> produtos = repository.findAll();
	   return produtos.stream()
			   .map(x -> new LocalDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public LocalDTO findOne(Long id) {
		Local local = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Produto não encontrado, id: "+id));
		return new LocalDTO(local);
	}
	
	@Transactional(readOnly = true)
	public Local findOneEntity(Long id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Produto não encontrado, id: "+id));
		
	}

	@Transactional(readOnly = false)
	public LocalDTO persist(LocalDTO dto) {
		Local local = new Local();
		copyToEntity(dto, local);
		repository.save(local);
		return new LocalDTO(local);
	}
	
	@Transactional(readOnly = false)
	public LocalDTO update(Long id, LocalDTO dto) {
		Local local = findOneEntity(id);
		copyToEntity(dto, local);
		repository.save(local);
		return new LocalDTO(local);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}

	}
	
	private void copyToEntity(LocalDTO dto, Local entity) {
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setSecretaria(secretariaService.findOnentity(dto.getSecretaria().getId()));
		entity.setEndereco(enderecoService.findOnentity(dto.getEndereco().getId()));
	}
}
