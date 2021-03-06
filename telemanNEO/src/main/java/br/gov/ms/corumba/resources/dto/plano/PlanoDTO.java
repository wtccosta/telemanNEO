package br.gov.ms.corumba.resources.dto.plano;

import java.io.Serializable;

import br.gov.ms.corumba.entities.Plano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String descricao;
	
	public PlanoDTO(Plano entity) {
		id = entity.getId();
		nome = entity.getNome();
		descricao = entity.getDescricao();
	}

}
