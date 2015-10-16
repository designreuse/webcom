package br.com.ftech.webcom.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ftech.webcom.entity.Role;
import br.com.ftech.webcom.entity.Usuario;
import br.com.ftech.webcom.entity.VendaCabecalho;
import br.com.ftech.webcom.repository.RoleRepository;
import br.com.ftech.webcom.repository.UsuarioRepository;
import br.com.ftech.webcom.repository.VendaCabecalhoRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VendaCabecalhoRepository  vendaRepository;

	@Autowired
	private RoleRepository roleRepository;

	@PersistenceContext
	private EntityManager manager;

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public void cadastrar(Usuario usuario) {
		DateTime data_cadastro = new DateTime();
		usuario.setData_cadastro(data_cadastro.toDate());
		usuarioRepository.save(usuario);
	}

	public void atualizar(Usuario usuario) {
		Usuario usuarioUpdate = usuarioRepository.findOne(usuario.getId_usuario());
		usuarioUpdate.setLogin(usuario.getLogin());
		usuarioUpdate.setNome(usuario.getNome());
		if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
			usuarioUpdate.setSenha(usuario.getSenha());
		}
		manager.merge(usuarioUpdate);
	}

	public void remover(Long idUsuario) {
		Usuario usuario = findById(idUsuario);
		List<Role> roles = roleRepository.findRolesByUser(usuario);
		if (!roles.isEmpty()) {
			for (Role r : roles) {
				r.setUsuario(null);
			}
		}
		List<VendaCabecalho> vendas = vendaRepository.buscaPorUsuario(usuario);
		if (!vendas.isEmpty()) {
			for (VendaCabecalho vc : vendas) {
				vc.setUsuario(null);
			}
		}
		usuarioRepository.delete(idUsuario);
	}

	public Usuario findById(Long id) {
		return usuarioRepository.findOne(id);
	}

	public Usuario buscaPorLoginSenha(String login, String senha) {
		return usuarioRepository.findByLoginAndSenha(login, senha);
	}

	public Usuario buscaPorLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

	public List<Usuario> findByName(String nome) {
		List<Usuario> usuarios = new ArrayList<>();
		if (nome != null && nome.trim() != "") {
			nome = "%" + nome.trim().toLowerCase() + "%";
			usuarios = usuarioRepository.buscaTodos(nome);
		}
		return usuarios;
	}

}