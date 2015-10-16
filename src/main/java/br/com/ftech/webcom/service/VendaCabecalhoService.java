package br.com.ftech.webcom.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.ftech.webcom.entity.VendaCabecalho;

public interface VendaCabecalhoService {
	
	VendaCabecalho findById(Long id);
	
	List<VendaCabecalho> findAll();

	void save(VendaCabecalho vendaCabecalho);
	
	void delete(Long id);

	void update(VendaCabecalho vendaCabecalho);
	

}
