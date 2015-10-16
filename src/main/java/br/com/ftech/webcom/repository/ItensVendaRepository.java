package br.com.ftech.webcom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ftech.webcom.entity.ItensVenda;
import br.com.ftech.webcom.entity.Produto;
import br.com.ftech.webcom.entity.VendaCabecalho;


public interface ItensVendaRepository extends JpaRepository<ItensVenda, Long>{
	
	
	@Query("Select iv from ItensVenda iv  where iv.vendaCabecalho = :venda")
	List<ItensVenda> buscaItensPorVenda(@Param("venda") VendaCabecalho venda); 
	
	
	@Query("from ItensVenda iv where iv.produto = :produto")
	public List<ItensVenda> buscaPorProduto(@Param("produto") Produto produto); 
	
}
