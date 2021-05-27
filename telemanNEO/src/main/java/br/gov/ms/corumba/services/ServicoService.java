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

import br.gov.ms.corumba.entities.Servico;
import br.gov.ms.corumba.repositories.ServicoRepository;
import br.gov.ms.corumba.resources.dto.servico.ServicoDTO;
import br.gov.ms.corumba.services.exceptions.DatabaseException;
import br.gov.ms.corumba.services.exceptions.ResourceNotFoundException;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repository;

	@Transactional(readOnly = true)
	public Servico findOneEntity(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Servico não encontrado, id: " + id));

	}

	@Transactional(readOnly = true)
	public Page<ServicoDTO> findAllPaged(Pageable paged) {
		Page<Servico> servico = repository.findAll(paged);
		return servico.map(x -> new ServicoDTO(x));
	}

	@Transactional(readOnly = true)
	public List<ServicoDTO> findAll() {
		List<Servico> servicos = repository.findAll();
		return servicos.stream().map(x -> new ServicoDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ServicoDTO findOne(Long id) {
		Servico servico = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado, id: " + id));
		return new ServicoDTO(servico);
	}

	@Transactional(readOnly = false)
	public ServicoDTO persist(ServicoDTO dto) {
		Servico servico = new Servico();
		copyToEntity(dto, servico);
		repository.save(servico);
		return new ServicoDTO(servico);
	}

	@Transactional(readOnly = false)
	public ServicoDTO update(Long id, ServicoDTO dto) {
		Servico servico = findOneEntity(id);
		copyToEntity(dto, servico);
		repository.save(servico);
		return new ServicoDTO(servico);
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

	private void copyToEntity(ServicoDTO dto, Servico entity) {
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
	}

}
