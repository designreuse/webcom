package br.com.ftech.webcom.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoPagamento {
	@Id
	private Long codigoTipoPagamento;
	private String descricaoTipoPagamento;

	public Long getCodigoTipoPagamento() {
		return codigoTipoPagamento;
	}

	public void setCodigoTipoPagamento(Long codigoTipoPagamento) {
		this.codigoTipoPagamento = codigoTipoPagamento;
	}

	public String getDescricaoTipoPagamento() {
		return descricaoTipoPagamento;
	}

	public void setDescricaoTipoPagamento(String descricaoTipoPagamento) {
		this.descricaoTipoPagamento = descricaoTipoPagamento;
	}

}
