package br.com.ftech.webcom.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ftech.webcom.entity.ItensVenda;
import br.com.ftech.webcom.entity.Produto;
import br.com.ftech.webcom.repository.ItensVendaRepository;
import br.com.ftech.webcom.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService{
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItensVendaRepository itensVendaRepository;
	
	
	@PersistenceContext
	private EntityManager manager;
	
	private static final int PAGE_SIZE = 5;
	
	@Override
	public Produto findById(String id) {
		Produto produto = produtoRepository.findOne(id);		
		return produto;
	}

	@Override
	public List<Produto> findAll() {
		return produtoRepository.findAll();

	}

	@Override
	public void save(Produto produto) {
		DateTime data_cadastro = new DateTime();
		produto.setData_cadastro(data_cadastro.toDate());
		produtoRepository.save(produto);
		
	}
	
	@Override
	public void update(Produto produto) {
		Produto produtoUpdate = findById(produto.getId_produto());
		produtoUpdate.setNome(produto.getNome());
		produtoUpdate.setPreco_compra(produto.getPreco_compra());
		produtoUpdate.setPreco_venda(produto.getPreco_venda());
		produtoUpdate.setQtde_estoque(produto.getQtde_estoque());
		produtoUpdate.setDescricao(produto.getDescricao());
		produtoUpdate.setFornecedor(produto.getFornecedor());
		produtoUpdate.setUnidade(produto.getUnidade());
		produtoUpdate.setData(produto.getData());
		manager.merge(produtoUpdate);
	}
	
	@Override
	public void delete(String id) {
		Produto produto= findById(id);
		List<ItensVenda> itens = itensVendaRepository.buscaPorProduto(produto);
		if (!itens.isEmpty()) {
			for (ItensVenda iv : itens) {
				iv.setProduto(null);
			}
		}
		
		produtoRepository.delete(id);

		
	}

	@Override
	public List<Produto> findByName(String nome) {
		List<Produto>produtos = new ArrayList<>();
		if (nome != null && nome.trim() != "") {
			nome= "%"+nome.trim().toLowerCase()+"%";
			produtos =produtoRepository.buscaTodos(nome);
		}
		return produtos;
	}

	@Override
	public void updateEstoque(Produto produto) {
		Produto produtoUpdate = findById(produto.getId_produto());
		produtoUpdate.setQtde_estoque(produto.getQtde_estoque());
		manager.merge(produtoUpdate);
	

		
	}

	@Override
	public byte[] loadImage(String id) {
		// TODO Auto-generated method stub
		return manager.find(Produto.class, id).getData();
	}

	
	
	
	

}
