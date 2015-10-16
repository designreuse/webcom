package br.com.ftech.webcom.dto;

import java.math.BigDecimal;

import br.com.ftech.webcom.entity.ItensVenda;

public class ItemVendaDTO {

	private Long id_itens_venda;
	private String nome_produto;
	private Integer qtde;
	private Integer sequencia;
	private String status_item;
	private BigDecimal sub_total;
	private BigDecimal valor_unitario;
	private String id_produto;
	private Long id_venda_cabecalho;

	public ItemVendaDTO(ItensVenda itemVenda) {
		super();
		this.id_itens_venda = itemVenda.getId_itens_venda();
		this.nome_produto = itemVenda.getNomeProduto();
		this.qtde = itemVenda.getQuantidade();
		this.sequencia = itemVenda.getSequencia();
		this.status_item = itemVenda.getStatusItem();
		this.sub_total = itemVenda.getSubTotal();
		this.valor_unitario = itemVenda.getValorUnitario();
		this.id_produto = itemVenda.getProduto().getId_produto();
		this.id_venda_cabecalho = itemVenda.getVendaCabecalho().getId_venda_cabecalho();
	}

	public Long getId_itens_venda() {
		return id_itens_venda;
	}

	public void setId_itens_venda(Long id_itens_venda) {
		this.id_itens_venda = id_itens_venda;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public String getStatus_item() {
		return status_item;
	}

	public void setStatus_item(String status_item) {
		this.status_item = status_item;
	}

	public BigDecimal getSub_total() {
		return sub_total;
	}

	public void setSub_total(BigDecimal sub_total) {
		this.sub_total = sub_total;
	}

	public BigDecimal getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(BigDecimal valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	
	public String getId_produto() {
		return id_produto;
	}

	public void setId_produto(String id_produto) {
		this.id_produto = id_produto;
	}

	public Long getId_venda_cabecalho() {
		return id_venda_cabecalho;
	}

	public void setId_venda_cabecalho(Long id_venda_cabecalho) {
		this.id_venda_cabecalho = id_venda_cabecalho;
	}

}
