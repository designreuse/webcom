package br.com.ftech.webcom.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ftech.webcom.entity.Cliente;
import br.com.ftech.webcom.entity.Usuario;
import br.com.ftech.webcom.entity.VendaCabecalho;


public interface VendaCabecalhoRepository extends JpaRepository<VendaCabecalho, Long>{
	
	
	@Query("from VendaCabecalho vb  where vb.dataHoraVenda between :dataInicial and :dataFim")
	public List<VendaCabecalho> buscaVendaPorPeriodo(@Param("dataInicial") Date dataInicial,@Param("dataFim") Date dataFim ); 


	@Query("from VendaCabecalho vb  where vb.cliente = :cliente")
	public List<VendaCabecalho> buscaPorCliente(@Param("cliente") Cliente cliente); 
	
	
	@Query("from VendaCabecalho vb  where vb.usuario = :usuario")
	public List<VendaCabecalho> buscaPorUsuario(@Param("usuario") Usuario usuario); 
	

}
