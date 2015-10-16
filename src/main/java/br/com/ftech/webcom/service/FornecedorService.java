package br.com.ftech.webcom.service;

import java.util.List;

import br.com.ftech.webcom.entity.Fornecedor;

public interface FornecedorService {
	
	Fornecedor findById(Long id);
	
	List<Fornecedor> findAll();
	
	List<Fornecedor> findByName(String nome);

	void save(Fornecedor fornecedor);
	
	void delete(Long id);

	void update(Fornecedor fornecedor);

}
