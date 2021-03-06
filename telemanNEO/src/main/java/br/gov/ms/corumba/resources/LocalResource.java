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

import br.gov.ms.corumba.resources.dto.local.LocalDTO;
import br.gov.ms.corumba.services.LocalService;

@RestController
@RequestMapping(value = "/locais")
public class LocalResource {

	@Autowired
	private LocalService service;
	
	@GetMapping()
	public ResponseEntity<List<LocalDTO>> findAll(){
		List<LocalDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "paged")
	public ResponseEntity<Page<LocalDTO>> findAllPaged(Pageable pageable){
		Page<LocalDTO> page = service.findAllPaged(pageable);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LocalDTO> detalhes(@PathVariable Long id){
		LocalDTO local = service.findOne(id);
		return ResponseEntity.ok(local);
	}
	
	@PostMapping
	public ResponseEntity<LocalDTO> create(@RequestBody LocalDTO dto){
		dto = service.persist(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
