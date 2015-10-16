package br.com.ftech.webcom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ftech.webcom.entity.Role;
import br.com.ftech.webcom.entity.Usuario;


public interface RoleRepository extends JpaRepository<Role, Long>{
	
	
	@Query("SELECT r from Role r where r.usuario = :usuario")
	public List<Role> findRolesByUser(@Param("usuario")Usuario usuario); 
	
	

	
}
