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

import br.gov.ms.corumba.entities.Plano;
import br.gov.ms.corumba.repositories.PlanoRepository;
import br.gov.ms.corumba.resources.dto.plano.PlanoDTO;
import br.gov.ms.corumba.services.exceptions.DatabaseException;
import br.gov.ms.corumba.services.exceptions.ResourceNotFoundException;

@Service
public class PlanoService {

	@Autowired
	private PlanoRepository repository;
	
	@Transactional(readOnly = true)
	public Plano findOneEntity(Long id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Plano não encontrado, id: "+id));
		
	}
	
	@Transactional(readOnly = true)
	public  Page<PlanoDTO> findAllPaged(Pageable paged) {
		Page<Plano> produtos = repository.findAll(paged);
	   return produtos.map(x -> new PlanoDTO(x));
	}
	
	@Transactional(readOnly = true)
	public  List<PlanoDTO> findAll() {
		List<Plano> produtos = repository.findAll();
	   return produtos.stream()
			   .map(x -> new PlanoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public PlanoDTO findOne(Long id) {
		Plano plano = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Produto não encontrado, id: "+id));
		return new PlanoDTO(plano);
	}
	

	@Transactional(readOnly = false)
	public PlanoDTO persist(PlanoDTO dto) {
		Plano plano = new Plano();
		copyToEntity(dto, plano);
		repository.save(plano);
		return new PlanoDTO(plano);
	}
	
	@Transactional(readOnly = false)
	public PlanoDTO update(Long id, PlanoDTO dto) {
		Plano plano = findOneEntity(id);
		copyToEntity(dto, plano);
		repository.save(plano);
		return new PlanoDTO(plano);
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
	
	private void copyToEntity(PlanoDTO dto, Plano entity) {
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
	}
}
