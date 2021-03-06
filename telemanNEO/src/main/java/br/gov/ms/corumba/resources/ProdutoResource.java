package br.gov.ms.corumba.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.ms.corumba.resources.dto.produtos.ListaProdutoDTO;
import br.gov.ms.corumba.resources.dto.produtos.ProdutoDTO;
import br.gov.ms.corumba.resources.dto.produtos.ProdutoInputDTO;
import br.gov.ms.corumba.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public ResponseEntity<Page<ListaProdutoDTO>>  produtos(Pageable pageable){
		Page<ListaProdutoDTO> page = service.findAllPaged(pageable);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value = "/{id}")
	public ProdutoDTO produtos(@PathVariable Long id){
		return service.findOne(id);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoInputDTO dto){
		ProdutoDTO produtoSalvo = service.persist(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(produtoSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(produtoSalvo);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody ProdutoInputDTO dto){
		service.update(id, dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
