package br.com.ftech.webcom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ftech.webcom.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

	@Query("from Fornecedor f  where lower(f.nome) like :nome order by f.nome ")
	List<Fornecedor> buscaTodosPorNome(@Param("nome") String nome);

}
