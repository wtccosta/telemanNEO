package br.gov.ms.corumba.resources.dto.produtos;

import java.time.LocalDate;

import br.gov.ms.corumba.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaProdutoDTO {

	
	private Long id;
	private String linhaOuRamal;
	private String contrato;
	private String agrupador;
	private LocalDate dataEdicao;
	
	public ListaProdutoDTO(Produto entity) {
		id = entity.getId();
		linhaOuRamal = entity.getLinhaOuRamal();
		contrato = entity.getContrato();
		agrupador = (entity.getAgrupador() == null ) ? null : entity.getAgrupador().getLinhaOuRamal();
		dataEdicao = entity.getDataEdicao();
	}
}
