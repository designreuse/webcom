package br.com.ftech.webcom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ftech.webcom.entity.Unidade;


public interface UnidadeRepository extends JpaRepository<Unidade, Long>{
	
	
	@Query("from Unidade u  where lower(u.descricao) like :descricao order by u.descricao ")
	Unidade buscaPorNome(@Param("descricao") String nome);
	
	
	

	
}
