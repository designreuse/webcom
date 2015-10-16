package br.com.ftech.webcom.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ftech.webcom.entity.Fornecedor;
import br.com.ftech.webcom.entity.Produto;
import br.com.ftech.webcom.entity.Unidade;


public interface ProdutoRepository extends JpaRepository<Produto, String>{
	
	@Query("from Produto p  where lower(p.nome) like :nome order by p.nome ")
	public List<Produto> buscaTodos(@Param("nome") String nome); 
	
	
	@Query("from Produto p  where p.unidade = :unidade")
	public List<Produto> buscaPorUnidade(@Param("unidade") Unidade unidade); 
	
	@Query("from Produto p  where p.fornecedor = :fornecedor")
	public List<Produto> buscaPorFornecedor(@Param("fornecedor") Fornecedor fornecedor); 
	
}

