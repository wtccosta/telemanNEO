package br.gov.ms.corumba.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ms.corumba.entities.Produto;
import br.gov.ms.corumba.repositories.ProdutosRepository;
import br.gov.ms.corumba.repositories.exceptions.DuplicateItemException;
import br.gov.ms.corumba.resources.dto.produtos.ListaProdutoDTO;
import br.gov.ms.corumba.resources.dto.produtos.ProdutoDTO;
import br.gov.ms.corumba.resources.dto.produtos.ProdutoInputDTO;
import br.gov.ms.corumba.services.exceptions.DatabaseException;
import br.gov.ms.corumba.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutosRepository repository;

	@Autowired
	private LocalService localService;

	@Autowired
	private PlanoService planoService;

	@Autowired
	private ServicoService ServicoService;

	@Autowired
	private PrecoService precoService;

	@Autowired
	private RoteadorService roteadorService;

	@Autowired
	private VelocidadeInternetService velocidadeInternetService;

	@Transactional(readOnly = true)
	public Page<ListaProdutoDTO> findAllPaged(Pageable pageable) {
		Page<Produto> produtos = repository.findAll(pageable);
		return produtos.map(x -> new ListaProdutoDTO(x));
	}

	@Transactional(readOnly = true)
	public ProdutoDTO findOne(Long id) {
		Produto produto = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado, id: " + id));
		return new ProdutoDTO(produto);
	}	

	@Transactional(readOnly = false)
	public ProdutoDTO persist(ProdutoInputDTO dto) {
		if (repository.findByLinhaOuRamal(dto.getLinhaOuRamal()).isPresent()) {
			throw new DuplicateItemException(
					"Número de terminal " + dto.getLinhaOuRamal() + " já existe. Vedada entrada de dados duplicados.");
		}
		try {
			Produto entity = new Produto();
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			populateNumerosAgrupados(dto, entity);
			return new ProdutoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + e.getMessage());
		}

	}

	@Transactional(readOnly = true)
	public Produto findOneEntity(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado, id: " + id));

	}

	//@Transactional(readOnly = false)
	public void update(Long id, ProdutoInputDTO dto) {
		Produto entity = findOneEntity(id);
		copyDtoToEntity(dto, entity);
//		if(dto.getNumerosAgrupados() != null || entity.getNumerosAgrupados() != null) {
//			entity.getNumerosAgrupados().clear();
//			List<Produto> agrupados = new ArrayList<>();
//			for(Long prodId : dto.getNumerosAgrupados()) {
//				System.out.println(prodId);
//				Produto prodAgrupado = findOneEntity(prodId);
////				if(prodAgrupado != null) {
//					agrupados.add(prodAgrupado);
////				}	
//			}
//			entity.setNumerosAgrupados(agrupados);
//		}
		repository.saveAndFlush(entity);
	}

	@Transactional
	public void delete(Long id) {

		try {
			Produto pro = repository.findById(id).orElseThrow();
			repository.delete(pro);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void copyDtoToEntity(ProdutoInputDTO dto, Produto entity) {
		if (dto.getId() == null) {
			entity.setLinhaOuRamal(dto.getLinhaOuRamal());
		}
		entity.setContrato(dto.getContrato());
		entity.setAgrupador(findOneEntity(dto.getAgrupadorId()));
		entity.setMsos(dto.getMsos());
		entity.setSenha(dto.getSenha());
		entity.setLocal(localService.findOneEntity(dto.getLocalId()));
		entity.setServico(ServicoService.findOneEntity(dto.getServicoId()));
		entity.setPlano(planoService.findOneEntity(dto.getPlanoId()));
		entity.setPrecoServico(precoService.findOneEntity(dto.getPrecoServicoId()));
		entity.setPrecoPlano(precoService.findOneEntity(dto.getPrecoPlanoId()));
		entity.setRoteador(roteadorService.findOneEntity(dto.getRoteadorId()));
		entity.setVelocidade(velocidadeInternetService.findOneEntity(dto.getVelocidadeInternetId()));
		entity.setDiaVencimento(dto.getDiaVencimento());
		entity.setDataInstalacao(dto.getDataInstalacao());
//
//		Set<Produto> auxList = new HashSet<>();
//		for (Long agrupadorId : dto.getNumerosAgrupados()) {
//			Produto prod = findOneEntity(agrupadorId);
//			auxList.add(prod);
//		}
//		entity.setNumerosAgrupados(auxList);

	}

	private void populateNumerosAgrupados(ProdutoInputDTO dto, Produto entity) {
		if (entity.getNumerosAgrupados() != null) {
			entity.getNumerosAgrupados().clear();
		}
		for (Long agrupadorId : dto.getNumerosAgrupados()) {
			Produto prod = findOneEntity(agrupadorId);
			prod.setAgrupador(entity);
		}
	}

}
