package br.gov.ms.corumba.resources.dto.produtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProdutoInputDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String linhaOuRamal;
	private String contrato;
	private Long agrupadorId;
	private String msos;
	private String senha;
	private List<Long> numerosAgrupados = new ArrayList<>();
	private Long localId;
	private Long servicoId;
	private Long planoId;
	private Long precoPlanoId;
	private Long precoServicoId;
	private Long roteadorId;
	private Long velocidadeInternetId;
	private Integer diaVencimento;
	private LocalDate dataInstalacao;
	private LocalDate dataCadastro;
	private LocalDate dataEdicao;
	
}
