package br.gov.ms.corumba.resources.dto.preco;

import java.io.Serializable;

import br.gov.ms.corumba.entities.Preco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double valor;
	
	public PrecoDTO(Preco entity) {
		id = entity.getId();
		valor = entity.getValor();
	}

}
