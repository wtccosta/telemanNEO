package br.gov.ms.corumba.resources.dto.servico;

import java.io.Serializable;

import br.gov.ms.corumba.entities.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String descricao;
	
	public ServicoDTO(Servico entity) {
		id = entity.getId();
		nome = entity.getNome();
		descricao = entity.getDescricao();

	}

}
