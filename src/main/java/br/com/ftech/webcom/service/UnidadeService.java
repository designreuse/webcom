package br.com.ftech.webcom.service;

import java.util.List;

import br.com.ftech.webcom.entity.Unidade;

public interface UnidadeService {
	
	Unidade findById(Long id);
	
	List<Unidade> findAll();
	
	void save(Unidade unidade);
	
	void delete(Long id);

	void update(Unidade unidade);
	
	
	Unidade findByName(String nome);

}
