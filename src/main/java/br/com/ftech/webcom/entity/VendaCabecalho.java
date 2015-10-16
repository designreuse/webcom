package br.com.ftech.webcom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venda_cabecalho")
public class VendaCabecalho implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9025916987491725968L;
	@Id
	@GeneratedValue
	@Column(name = "id_venda_cabecalho")
	private Long id_venda_cabecalho;
	@Column(name = "qtde_itens")
	private Integer qtdeItens;
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	@Column(name = "status_venda")
	private String statusVenda;
	@Column(name = "data_hora_venda")
	private Date dataHoraVenda;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "id_cartao")
	private Cartao cartao;
	
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_pagamento")
	private TipoPagamento tipoPagamento;
	
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public void setId_venda_cabecalho(Long id_venda_cabecalho) {
		this.id_venda_cabecalho = id_venda_cabecalho;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Long getId_venda_cabecalho() {
		return id_venda_cabecalho;
	}

	public void setId(Long id) {
		this.id_venda_cabecalho = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getQtdeItens() {
		return qtdeItens;
	}

	public void setQtdeItens(Integer qtdeItens) {
		this.qtdeItens = qtdeItens;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(String statusVenda) {
		this.statusVenda = statusVenda;
	}

	public Date getDataHoraVenda() {
		return dataHoraVenda;
	}

	public void setDataHoraVenda(Date dataHoraVenda) {
		this.dataHoraVenda = dataHoraVenda;
	}

	

}
