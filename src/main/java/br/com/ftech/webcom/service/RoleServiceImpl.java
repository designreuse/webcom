package br.com.ftech.webcom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ftech.webcom.entity.Role;
import br.com.ftech.webcom.entity.Usuario;
import br.com.ftech.webcom.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	
	@Override
	public List<Role> buscaPermissoesPorUsuario(Usuario usuario) {
		
		return roleRepository.findRolesByUser(usuario);
	}


	@Override
	public void save(Role role) {
      roleRepository.save(role)		;
	}
}