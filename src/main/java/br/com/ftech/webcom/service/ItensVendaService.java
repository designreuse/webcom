package br.com.ftech.webcom.service;

import java.util.List;

import br.com.ftech.webcom.entity.ItensVenda;
import br.com.ftech.webcom.entity.VendaCabecalho;

public interface ItensVendaService {
	
	ItensVenda findById(Long id);
	
	List<ItensVenda > findAll();

	void save(ItensVenda  itensVenda );
	
	void delete(Long id);

	void update(ItensVenda  itensVenda );
	
	void updateStatus(ItensVenda itensVenda);
	

	List<ItensVenda> buscaPorVenda(VendaCabecalho venda);

}
