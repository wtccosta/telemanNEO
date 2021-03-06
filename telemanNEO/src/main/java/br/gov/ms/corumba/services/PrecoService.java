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

import br.gov.ms.corumba.entities.Preco;
import br.gov.ms.corumba.repositories.PrecoRepository;
import br.gov.ms.corumba.resources.dto.preco.PrecoDTO;
import br.gov.ms.corumba.services.exceptions.DatabaseException;
import br.gov.ms.corumba.services.exceptions.ResourceNotFoundException;

@Service
public class PrecoService {

	@Autowired
	private PrecoRepository repository;
	
	@Transactional(readOnly = true)
	public Preco findOneEntity(Long id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Preço não encontrado, id: "+id));
		
	}
	
	@Transactional(readOnly = true)
	public  Page<PrecoDTO> findAllPaged(Pageable paged) {
		Page<Preco> produtos = repository.findAll(paged);
	   return produtos.map(x -> new PrecoDTO(x));
	}
	
	@Transactional(readOnly = true)
	public  List<PrecoDTO> findAll() {
		List<Preco> produtos = repository.findAll();
	   return produtos.stream()
			   .map(x -> new PrecoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public PrecoDTO findOne(Long id) {
		Preco preco = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Produto não encontrado, id: "+id));
		return new PrecoDTO(preco);
	}
	
	@Transactional(readOnly = false)
	public PrecoDTO persist(PrecoDTO dto) {
		Preco preco = new Preco();
		copyToEntity(dto, preco);
		repository.save(preco);
		return new PrecoDTO(preco);
	}
	
	@Transactional(readOnly = false)
	public PrecoDTO update(Long id, PrecoDTO dto) {
		Preco preco = findOneEntity(id);
		copyToEntity(dto, preco);
		repository.save(preco);
		return new PrecoDTO(preco);
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
	
	private void copyToEntity(PrecoDTO dto, Preco entity) {
		entity.setValor(dto.getValor());
	}
}
