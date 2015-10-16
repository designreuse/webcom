package br.com.ftech.webcom.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RelatorioVendaDTO {
		
	
		private String nomeCliente;
		private Date dataVenda;
		private String nomeProduto;
		private Integer Quantidade;
		private BigDecimal valorUnitario;
		private BigDecimal valorTotal;
		
		
		public String getNomeCliente() {
			return nomeCliente;
		}
		public void setNomeCliente(String nomeCliente) {
			this.nomeCliente = nomeCliente;
		}
		public Date getDataVenda() {
			return dataVenda;
		}
		public void setDataVenda(Date dataVenda) {
			this.dataVenda = dataVenda;
		}
		public String getNomeProduto() {
			return nomeProduto;
		}
		public void setNomeProduto(String nomeProduto) {
			this.nomeProduto = nomeProduto;
		}
		public Integer getQuantidade() {
			return Quantidade;
		}
		public void setQuantidade(Integer quantidade) {
			Quantidade = quantidade;
		}
		public BigDecimal getValorUnitario() {
			return valorUnitario;
		}
		public void setValorUnitario(BigDecimal valorUnitario) {
			this.valorUnitario = valorUnitario;
		}
		public BigDecimal getValorTotal() {
			return valorTotal;
		}
		public void setValorTotal(BigDecimal valorTotal) {
			this.valorTotal = valorTotal;
		}
		
		
		
}
