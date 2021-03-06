package br.gov.ms.corumba.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "produto")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "linha_ou_ramal")
	private String linhaOuRamal;

	@Column(name = "contrato")
	private String contrato;

	@ManyToOne
	@JoinColumn(name = "agrupador_id")
	private Produto agrupador;

	@JsonIgnore
	@OneToMany(mappedBy = "agrupador", targetEntity = Produto.class, cascade = CascadeType.MERGE, orphanRemoval = false)
	private Set<Produto> numerosAgrupados = new HashSet<>();

	private String msos;

	private String senha;

	@ManyToOne
	@JoinColumn(name = "local_id")
	private Local local;

	@ManyToOne
	@JoinColumn(name = "servico_operadora_id")
	private Servico servico;

	@ManyToOne
	@JoinColumn(name = "plano_operadora_id")
	private Plano plano;

	@ManyToOne
	@JoinColumn(name = "preco_do_plano_id")
	private Preco precoPlano;

	@ManyToOne
	@JoinColumn(name = "preco_do_servico_id")
	private Preco precoServico;

	@ManyToOne
	@JoinColumn(name = "roteador_id")
	private Roteador roteador;

	@ManyToOne
	@JoinColumn(name = "velocidade_internet_id")
	private VelocidadeInternet velocidade;

	@Column(name = "dia_vencimento")
	private Integer diaVencimento;

	@Column(name = "data_instalacao")
	private LocalDate dataInstalacao;

	@CreationTimestamp
	@Column(name = "data_criacao")
	private LocalDate dataCadastro;

	@UpdateTimestamp
	@Column(name = "data_edicao")
	private LocalDate dataEdicao;

	@PreRemove
	private void antesDeRemover() {
	
		if (numerosAgrupados != null) {
			for (Produto x : this.numerosAgrupados) {
			//	System.out.println(x.getAgrupador());
				x.setAgrupador(null);
			}
		}
	}

}
