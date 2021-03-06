package br.gov.ms.corumba.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.ms.corumba.resources.dto.preco.PrecoDTO;
import br.gov.ms.corumba.services.PrecoService;

@RestController
@RequestMapping(value = "/precos")
public class PrecoResource {

	@Autowired
	private PrecoService service;
	
	@GetMapping()
	public ResponseEntity<List<PrecoDTO>> findAll(){
		List<PrecoDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "paged")
	public ResponseEntity<Page<PrecoDTO>> findAllPaged(Pageable pageable){
		Page<PrecoDTO> page = service.findAllPaged(pageable);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PrecoDTO> detalhes(@PathVariable Long id){
		PrecoDTO preco = service.findOne(id);
		return ResponseEntity.ok(preco);
	}
	
	@PostMapping
	public ResponseEntity<PrecoDTO> create(@RequestBody PrecoDTO dto){
		dto = service.persist(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
