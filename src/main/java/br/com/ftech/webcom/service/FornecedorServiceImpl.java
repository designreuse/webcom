package br.com.ftech.webcom.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ftech.webcom.entity.Fornecedor;
import br.com.ftech.webcom.entity.Produto;
import br.com.ftech.webcom.repository.FornecedorRepository;
import br.com.ftech.webcom.repository.ProdutoRepository;

@Service
@Transactional
public class FornecedorServiceImpl implements FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Fornecedor findById(Long id) {
		return fornecedorRepository.findOne(id);
	}

	@Override
	public List<Fornecedor> findAll() {
		return fornecedorRepository.findAll();
	}

	@Override
	public void save(Fornecedor fornecedor) {
		Date dataCadastro = new Date();
		fornecedor.setDataCadastro(dataCadastro);
		fornecedorRepository.save(fornecedor);
	}

	@Override
	public void update(Fornecedor fornecedor) {
		Fornecedor fornecedorUpdate = findById(fornecedor.getIdFornecedor());
		fornecedorUpdate.setNome(fornecedor.getNome());
		fornecedorUpdate.setRg(fornecedor.getRg());
		fornecedorUpdate.setTelefone(fornecedor.getTelefone());
		fornecedorUpdate.setBairro(fornecedor.getBairro());
		fornecedorUpdate.setCidade(fornecedor.getCidade());
		fornecedorUpdate.setCpf(fornecedor.getCpf());
		fornecedorUpdate.setCnpj(fornecedor.getCnpj());
		fornecedorUpdate.setEmail(fornecedor.getEmail());
		fornecedorUpdate.setEndereco(fornecedor.getEndereco());
		fornecedorUpdate.setEstado(fornecedor.getEstado());
		fornecedorUpdate.setObservacao(fornecedor.getObservacao());
		manager.merge(fornecedorUpdate);

	}

	@Override
	public void delete(Long id) {
		
		Fornecedor fornecedor = fornecedorRepository.findOne(id);
		List<Produto>produtos = produtoRepository.buscaPorFornecedor(fornecedor);
		if(!produtos.isEmpty()){
			for(Produto p : produtos){
				p.setFornecedor(null);
			}
		}
		fornecedorRepository.delete(id);
	}

	@Override
	public List<Fornecedor> findByName(String nome) {
		List<Fornecedor> fornecedors = new ArrayList<>();
		if (nome != null && nome.trim() != "") {
			nome= "%"+nome.trim().toLowerCase()+"%";
			fornecedors =fornecedorRepository.buscaTodosPorNome(nome);
		}
		return fornecedors;
	}
}
