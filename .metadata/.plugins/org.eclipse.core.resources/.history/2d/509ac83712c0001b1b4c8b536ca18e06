package br.gov.ms.corumba.resources.dto.produtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.gov.ms.corumba.entities.Produto;
import lombok.Data;

@Data
public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String linhaOuRamal;
	private String contrato;
	private String agrupador;
	private Integer totalNumerosAgrupados = 0;
	private Set<ListaProdutoDTO> numerosAgrupados = new HashSet<>();
	private String local;
	private String plano;
	private String servico;
	private Double precoPlano;
	private Double precoServico;
	private String roteador;
	private Integer diaVencimento;
	private LocalDate dataInstalacao;
	private LocalDate dataCadastro;
	private LocalDate dataEdicao;
	
	public ProdutoDTO(Produto entity) {
		id = entity.getId();
		linhaOuRamal = entity.getLinhaOuRamal();
		contrato = entity.getContrato();
		agrupador = (entity.getAgrupador() != null) ? entity.getAgrupador().getLinhaOuRamal() : null;
		local = entity.getLocal().getNome();
		servico = entity.getServico().getNome();
		plano = (entity.getPlano() != null) ? entity.getPlano().getNome() : "";
		precoPlano = entity.getPrecoPlano().getValor();
		precoServico = ( entity.getPrecoServico() != null) ? entity.getPrecoServico().getValor() : null;
		roteador = (entity.getRoteador() != null) ? entity.getRoteador().getNome() : null;
		diaVencimento = entity.getDiaVencimento();
		dataInstalacao = entity.getDataInstalacao();
		dataCadastro = entity.getDataCadastro();
		dataEdicao = entity.getDataEdicao();
		numerosAgrupados = convertToListaProductDto(entity);
		totalNumerosAgrupados = (entity.getNumerosAgrupados() != null) ? entity.getNumerosAgrupados().size() : null;
	}
	
	private Set<ListaProdutoDTO> convertToListaProductDto(Produto entity){
		Set<Produto> produtos = entity.getNumerosAgrupados();
		if(produtos == null || produtos.isEmpty()) {
			return null;
		}
		return produtos.stream()
				.map(x-> new ListaProdutoDTO(x))
				.collect(Collectors.toSet());
	}
	
	
}
