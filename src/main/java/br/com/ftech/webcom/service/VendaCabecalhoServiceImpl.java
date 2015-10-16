package br.com.ftech.webcom.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ftech.webcom.entity.VendaCabecalho;
import br.com.ftech.webcom.repository.VendaCabecalhoRepository;

@Service
@Transactional
public class VendaCabecalhoServiceImpl implements VendaCabecalhoService {

	@Autowired
	private VendaCabecalhoRepository vendaCabecalhoRepository;
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public VendaCabecalho findById(Long id) {
		return vendaCabecalhoRepository.findOne(id);
	}

	@Override
	public List<VendaCabecalho> findAll() {
		return vendaCabecalhoRepository.findAll();
	}

	@Override
	public void save(VendaCabecalho vendaCabecalho) {
		vendaCabecalhoRepository.save(vendaCabecalho);
	}

	@Override
	public void update(VendaCabecalho vendaCabecalho) {
		VendaCabecalho vendaCabecalhoUpdate = findById(vendaCabecalho.getId_venda_cabecalho());
		vendaCabecalhoUpdate.setQtdeItens(vendaCabecalho.getQtdeItens());
		vendaCabecalhoUpdate.setStatusVenda(vendaCabecalho.getStatusVenda());
		vendaCabecalhoUpdate.setValorTotal(vendaCabecalho.getValorTotal());
		manager.merge(vendaCabecalhoUpdate);

	}

	@Override
	public void delete(Long id) {
		vendaCabecalhoRepository.delete(id);
	}

	
}
