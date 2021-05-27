package br.gov.ms.corumba.resources.dto.local;

import java.io.Serializable;

import br.gov.ms.corumba.entities.Endereco;
import br.gov.ms.corumba.entities.Local;
import br.gov.ms.corumba.entities.Secretaria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String descricao;
	private Secretaria secretaria;
	private Endereco endereco;
	
	public LocalDTO(Local entity) {
		id = entity.getId();
		nome = entity.getNome();
		descricao = entity.getDescricao();
		secretaria = entity.getSecretaria();
		endereco = entity.getEndereco();
	}

}
