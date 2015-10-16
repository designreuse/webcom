package br.com.ftech.webcom.service;

import java.util.List;

import br.com.ftech.webcom.entity.Cliente;

public interface ClienteService {
	
	Cliente findById(Long id);
	
	List<Cliente> findAll();
	
	List<Cliente> findByName(String nome);

	void save(Cliente cliente);
	
	void delete(Long id);

	void update(Cliente cliente);

}
