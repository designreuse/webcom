package br.com.ftech.webcom.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.ftech.webcom.entity.Produto;

public interface ProdutoService {
	
	Produto findById(String id);
	
	List<Produto> findAll();

	void save(Produto produto);
	
	void delete(String id);

	void update(Produto produto);

	List<Produto> findByName(String nome);

	void updateEstoque(Produto produto);
	
	byte[] loadImage(String id);
	
	
}
