package br.com.ftech.webcom.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ftech.webcom.entity.Cliente;
import br.com.ftech.webcom.entity.VendaCabecalho;
import br.com.ftech.webcom.repository.ClienteRepository;
import br.com.ftech.webcom.repository.VendaCabecalhoRepository;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private VendaCabecalhoRepository vendaRepository;

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Cliente findById(Long id) {
		return clienteRepository.findOne(id);
	}

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public void save(Cliente cliente) {
		DateTime data_cadastro = new DateTime();
		cliente.setData_cadastro(data_cadastro.toDate());
		clienteRepository.save(cliente);
	}

	@Override
	public void update(Cliente cliente) {
		Cliente clienteUpdate = findById(cliente.getId_cliente());
		clienteUpdate.setNome(cliente.getNome());
		clienteUpdate.setRg(cliente.getRg());
		clienteUpdate.setTelefone(cliente.getTelefone());
		clienteUpdate.setBairro(cliente.getBairro());
		clienteUpdate.setCidade(cliente.getCidade());
		clienteUpdate.setCpf(cliente.getCpf());
		clienteUpdate.setObservacao(cliente.getObservacao());
		clienteUpdate.setCnpj(cliente.getCnpj());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setEndereco(cliente.getEndereco());
		clienteUpdate.setEstado(cliente.getEstado());
		manager.merge(clienteUpdate);

	}

	@Override
	public void delete(Long id) {

		Cliente cliente = clienteRepository.findOne(id);
		List<VendaCabecalho>vendas = vendaRepository.buscaPorCliente(cliente);
		if(!vendas.isEmpty()){
			for(VendaCabecalho vc: vendas){
				vc.setCliente(null);
			}
		}
		clienteRepository.delete(id);
	}

	@Override
	public List<Cliente> findByName(String nome) {
		List<Cliente> clientes = new ArrayList<>();
		if (nome != null && nome.trim() != "") {
			nome= "%"+nome.trim().toLowerCase()+"%";
			clientes =clienteRepository.buscaTodos(nome);
		}
		return clientes;
	}
}
