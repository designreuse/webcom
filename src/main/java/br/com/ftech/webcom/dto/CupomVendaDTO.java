package br.com.ftech.webcom.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CupomVendaDTO {

	private Date data;
	private BigDecimal vlTotal;
	private List<ItemVendaDTO> lista;

	public List<ItemVendaDTO> getLista() {
		return lista;
	}

	public void setLista(List<ItemVendaDTO> lista) {
		this.lista = lista;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getVlTotal() {
		return vlTotal;
	}

	public void setVlTotal(BigDecimal vlTotal) {
		this.vlTotal = vlTotal;
	}

}
