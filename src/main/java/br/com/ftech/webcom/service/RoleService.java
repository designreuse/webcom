package br.com.ftech.webcom.service;

import java.util.List;


import br.com.ftech.webcom.entity.Role;
import br.com.ftech.webcom.entity.Usuario;

public interface RoleService {
	
	
	
	List<Role> buscaPermissoesPorUsuario(Usuario usuario);

	void save(Role role);
	
}
