package br.com.ftech.webcom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ftech.webcom.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
	@Query("from Usuario where login = :login and senha = :senha")
	public Usuario findByLoginAndSenha(@Param("login")String login, @Param("senha") String senha); 
	
	Usuario findByLogin(String login);
	
	@Query("from Usuario u  where lower(u.nome) like :nome order by u.nome ")
	public List<Usuario> buscaTodos(@Param("nome") String nome); 
	

}
