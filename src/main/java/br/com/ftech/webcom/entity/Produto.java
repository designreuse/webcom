package br.com.ftech.webcom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Produto implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 268782080103153977L;
	@Id
	private String id_produto;
	@Column
	private String nome;
	private String descricao;
	private BigDecimal preco_compra;
	private BigDecimal preco_venda;
	private Double qtde_estoque;
	private Date data_cadastro;
	@ManyToOne
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;
	@Lob
    @Column( nullable=false, columnDefinition="mediumblob")
	@Basic(fetch=FetchType.LAZY)
    byte[] data;

	

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getId_produto() {
		return id_produto;
	}

	public void setId_produto(String id_produto) {
		this.id_produto = id_produto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco_compra() {
		return preco_compra;
	}

	public void setPreco_compra(BigDecimal preco_compra) {
		this.preco_compra = preco_compra;
	}

	public BigDecimal getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(BigDecimal preco_venda) {
		this.preco_venda = preco_venda;
	}

	public Double getQtde_estoque() {
		return qtde_estoque;
	}

	public void setQtde_estoque(Double qtde_estoque) {
		this.qtde_estoque = qtde_estoque;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

}
