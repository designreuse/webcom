package br.com.ftech.webcom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ftech.webcom.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	
	@Query("from Cliente c  where lower(c.nome) like :nome order by c.nome ")
	public List<Cliente> buscaTodos(@Param("nome") String nome); 
	
	
}
