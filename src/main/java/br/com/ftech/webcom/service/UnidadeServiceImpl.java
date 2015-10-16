package br.com.ftech.webcom.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ftech.webcom.entity.Produto;
import br.com.ftech.webcom.entity.Unidade;
import br.com.ftech.webcom.repository.ProdutoRepository;
import br.com.ftech.webcom.repository.UnidadeRepository;

@Service
@Transactional
public class UnidadeServiceImpl implements UnidadeService {

	@Autowired
	private UnidadeRepository unidadeRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Unidade findById(Long id) {
		return unidadeRepository.findOne(id);
	}

	@Override
	public List<Unidade> findAll() {
		return unidadeRepository.findAll();
	}

	@Override
	public void save(Unidade unidade) {
		unidadeRepository.save(unidade);
	}

	@Override
	public void update(Unidade unidade) {
		Unidade unidadeUpdate = findById(unidade.getIdUnidade());
		unidadeUpdate.setDescricao(unidade.getDescricao());
		manager.merge(unidadeUpdate);

	}

	@Override
	public void delete(Long id) {
		Unidade unidade = findById(id);
		List<Produto>produtos = produtoRepository.buscaPorUnidade(unidade);
		if(!produtos.isEmpty()){
			for(Produto p : produtos){
				p.setUnidade(null);
			}
		}		
		unidadeRepository.delete(id);
	}

	@Override
	public Unidade findByName(String nome) {
		Unidade unidade = new Unidade();
		
		if (nome != null && nome.trim() != "") {
			nome= "%"+nome.trim().toLowerCase()+"%";
			unidade =unidadeRepository.buscaPorNome(nome);
		}
		return unidade;
	}

	

	
}
