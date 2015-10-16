package br.com.ftech.webcom.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ftech.webcom.entity.Role;
import br.com.ftech.webcom.entity.Usuario;
import br.com.ftech.webcom.service.RoleService;
import br.com.ftech.webcom.service.UsuarioService;

@Service

public class WebcomAuthenticationService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {

		List<GrantedAuthority> listGrantAuthority = new ArrayList<GrantedAuthority>();

		Usuario usuario = usuarioService.buscaPorLogin(login);
		List<Role>roles = roleService.buscaPermissoesPorUsuario(usuario);
				
		checkGrantAuthorities(usuario,roles, listGrantAuthority);

		UserDetails userDetails = validateUser(login, listGrantAuthority, usuario);

		return userDetails;

	}

	private void checkGrantAuthorities(Usuario usuario,List<Role>roles, List<GrantedAuthority> listGrantAuthority) {

		if (usuario != null && roles != null && roles.isEmpty() == false) {			
			for (Role roleUser : roles) {
				listGrantAuthority.add(new SimpleGrantedAuthority(roleUser.getRole()));
			}

		}
	}

	private UserDetails validateUser(String login, List<GrantedAuthority> listGrantAuthority, Usuario usuario) {

		UserDetails userDetails = null;

		if (usuario != null) {

			boolean accountNonLocked = true;

			boolean enabledUser = true;

			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;

			userDetails = new org.springframework.security.core.userdetails.User(login, usuario.getSenha(), enabledUser, accountNonExpired,
					credentialsNonExpired, accountNonLocked, listGrantAuthority);

		}
		return userDetails;

	}
}
