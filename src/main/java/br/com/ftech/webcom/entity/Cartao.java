package br.com.ftech.webcom.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cartao {
	@Id
	private Long codigoCartao;

	public Long getCodigoCartao() {
		return codigoCartao;
	}

	public void setCodigoCartao(Long codigoCartao) {
		this.codigoCartao = codigoCartao;
	}

	public String getNomeCartao() {
		return nomeCartao;
	}

	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}

	public Double getTaxaCartao() {
		return taxaCartao;
	}

	public void setTaxaCartao(Double taxaCartao) {
		this.taxaCartao = taxaCartao;
	}

	private String nomeCartao;
	private Double taxaCartao;

}
