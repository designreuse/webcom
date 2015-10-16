package br.com.ftech.webcom.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ftech.webcom.entity.ItensVenda;
import br.com.ftech.webcom.entity.VendaCabecalho;
import br.com.ftech.webcom.repository.ItensVendaRepository;

@Service
@Transactional
public class ItensVendaServiceImpl implements ItensVendaService {

	@Autowired
	private ItensVendaRepository itemVendaRepository;
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public ItensVenda findById(Long id) {
		return itemVendaRepository.findOne(id);
	}

	@Override
	public List<ItensVenda> findAll() {
		return itemVendaRepository.findAll();
	}

	@Override
	public void save(ItensVenda itemVenda) {
		itemVendaRepository.save(itemVenda);
	}

	@Override
	public void update(ItensVenda itemVenda) {
		ItensVenda itemVendaUpdate = findById(itemVenda.getId_itens_venda());
		itemVendaUpdate.setNomeProduto(itemVenda.getNomeProduto());
		itemVendaUpdate.setProduto(itemVenda.getProduto());
		itemVendaUpdate.setQuantidade(itemVenda.getQuantidade());
		itemVendaUpdate.setSequencia(itemVenda.getSequencia());
		itemVendaUpdate.setStatusItem(itemVenda.getStatusItem());
		itemVendaUpdate.setSubTotal(itemVenda.getSubTotal());
		itemVendaUpdate.setValorUnitario(itemVenda.getValorUnitario());
		itemVendaUpdate.setVendaCabecalho(itemVenda.getVendaCabecalho());
		
		manager.merge(itemVendaUpdate);

	}

	@Override
	public void delete(Long id) {
		itemVendaRepository.delete(id);
	}

	@Override
	public List<ItensVenda> buscaPorVenda(VendaCabecalho venda) {
		
		
		return itemVendaRepository.buscaItensPorVenda(venda);
		
	}

	@Override
	public void updateStatus(ItensVenda itemVenda) {
		ItensVenda itemVendaUpdate = findById(itemVenda.getId_itens_venda());
		itemVendaUpdate.setStatusItem(itemVenda.getStatusItem());		
		manager.merge(itemVendaUpdate);

	}
}
