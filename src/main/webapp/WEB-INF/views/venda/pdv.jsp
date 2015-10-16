<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="springform" 	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="corpo">

	
	<div class='container'>

   <div class='panel panel-default'>
        <div class='panel-heading'><h3>Vendas <span class='label label-default'></span></h3></div>
            <div class='panel-body'>
	           <div class'row'>
		          <div class='col-md-12'>                                  
                    <select class='btn btn-default' id='select_cliente'></select>
                    <button class='btn btn-default btn-primary' id='btn_iniciar_venda'>Iniciar venda</button>
                    <button class='btn btn-default btn btn-danger' disabled='true' id='btn_encerrar_venda' onclick='encerrarVenda()'>Encerrar venda</button>
                    <span class='label label-default'><label>STATUS: </label></span> &nbsp;
                    <label id='status_venda'>Encerrada</label>&nbsp;
                    <span class='label label-default'><label>CÓD. VENDA: </label></span> &nbsp;
                    <label id='id_venda_cabecalho'></label> &nbsp;
                    <span class='label label-default'><label>CLIENTE: </label></span>     &nbsp;               
                    <label id='codigo_cliente'></label>&nbsp; 
                    <label id='cliente_nome'></label>                      
                 </div>
                </div>
            </div>
    </div>
    
    <div class='panel panel-default' id='div_venda_itens' hidden='hidden'>
            <div class='panel-body'>
                <div class=row>
                    <div class='col-md-12'> 
                        <div class='breadcrumb'>
                          <h3><label for='nomeProduto' id='nomeProduto'></label></h3>
                        </div>
                    </div>
                </div>
	           <div class'row'>
		          
                   <div class='col-md-3'>
                       <div class='breadcrumb'>
                        <div class='form-group'>
                           <label for='codigo_produto'>Código produto:</label>
                           <input type='text' class='form-control' id='codigo_produto'>
                           <label for='qtde'>Quantidade:</label>
                           <input type='number' class='form-control' id='qtde_item'>
                           <label for='preco'>Preço unitário:</label>
                           <input type='number' class='form-control' id='preco_unitario' disabled='true'>
                           <label for='codigo_produto'>Valor total:</label>
                           <input type='number' class='form-control' id='valor_total_item' disabled='true'>                           
                        </div>
                       <button class='btn btn-primary' id='btnAdicionarItem'>Adicionar ITEM</button>
                       </div>
                  </div>
                   
                  
                   
                  <div class='col-md-7'>
                      <div class='breadcrumb'>
                          <input type='number' id='contadorSeqItemVenda' disabled='true' value='1' hidden='hidden'> 
                          
                          <table class='table table-striped' id='tabela_cupom'>                              
                          </table>
                      </div>
                  </div>                   
                </div>
                
                <div class'row'>
		          <div class='col-md-12'> 
                      <div class='breadcrumb'>
                        <h3>
                            <label for='rotulo_total_geral'>TOTAL GERAL:</label>
                            <label id='total_geral' disabled='true'>                            
                        </h3>
                      </div>
                  </div>
               </div>
                
            </div>
        </div>                
    </div>
	
	
	<script src="${pageContext.request.contextPath}/recursos/js/javascript_pdv.js"></script>
	
	
	<script type="text/javascript">
          $(window).ready(function() {               
			  carregarNoLoadPagina();              
		  });
	</script> 
	
		




	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
