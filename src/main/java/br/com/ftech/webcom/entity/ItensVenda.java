package br.com.ftech.webcom.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="itens_venda")
public class ItensVenda implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4308661465838474890L;
	@Id
	@GeneratedValue
	@Column(name="id_itens_venda")
	private Long id_itens_venda;
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	@ManyToOne
	@JoinColumn(name = "id_venda_cabecalho")
	private VendaCabecalho vendaCabecalho;
	@Column(name="nome_produto")
	private String nomeProduto;
	@Column(name="sequencia")
	private Integer sequencia;
	@Column(name="qtde")
	private Integer quantidade;
	
	public Long getId_itens_venda() {
		return id_itens_venda;
	}
	public void setId_itens_venda(Long id) {
		this.id_itens_venda = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public VendaCabecalho getVendaCabecalho() {
		return vendaCabecalho;
	}
	public void setVendaCabecalho(VendaCabecalho vendaCabecalho) {
		this.vendaCabecalho = vendaCabecalho;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Integer getSequencia() {
		return sequencia;
	}
	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public String getStatusItem() {
		return statusItem;
	}
	public void setStatusItem(String statusItem) {
		this.statusItem = statusItem;
	}
	@Column(name="valor_unitario")
	private BigDecimal valorUnitario;
	@Column(name="sub_total")
	private BigDecimal subTotal;
	@Column(name="status_item")
	private String statusItem;
	
	
	

}
