<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<link rel="stylesheet"href="${pageContext.request.contextPath}/recursos/css/style-produto.css">
	
	<tiles:insertDefinition name="template">
		<tiles:putAttribute name="corpo">
		
						
		<h1>
			Cadastro de Produtos
		</h1>
		
			<form method="get"	action="${pageContext.request.contextPath}/protected/produto/busca-nome.do"	class="form-inline col-md-6" role="form">
				<div class="form-group  ">
					<label class="control-label" for="">Pesquisar produtos:</label>
					<input type="text" class="form-control" name="nome" autofocus="true" placeholder="Nome do produto">
				</div>
				<button type="submit" class="btn btn-default  ">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</form>
					
			 <a
				href="${pageContext.request.contextPath}/protected/preparaCadastroProduto.do">
				<input type="button" value="Adicionar produto" class="btn btn-default pull-right" />
			</a>		
			
			<table  class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Código</th>
						<th>Nome</th>
						<th>Descrição</th>
						<th>Preço de Compra - R$</th>
						<th>Preço de Venda - R$</th>
						<th>Quantidade</th>
						<th>Data de Cadastro</th>
						<th>Fornecedor</th>
						<th>Unidade</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${produtos}">
						<tr>
							<td>${p.id_produto}</td>
							<td>${p.nome}</td>
							<td>${p.descricao}</td>
							<td>${p.preco_compra}</td>
							<td>${p.preco_venda}</td>
							<td>${p.qtde_estoque}</td>
							<td>${p.data_cadastro}</td>
							<td>${p.fornecedor.nome}</td>
							<td>${p.unidade.descricao}</td>
							<td class="imagem">
							<img src="${pageContext.request.contextPath}/protected/produto/image.do?idProduto=${p.id_produto}"
											alt=" imagem do produto"></td>
							<td>
							
							<a href="${pageContext.request.contextPath}/protected/produto/preparaEdit.do?idProduto=${p.id_produto}">
									          <span class="glyphicon glyphicon-edit"></span> 
									
								</a>
							
							
							&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="${pageContext.request.contextPath}/protected/produto/excluir.do?idProduto=${p.id_produto}">
									          <span class="glyphicon glyphicon-trash"></span> 
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</tiles:putAttribute>
	</tiles:insertDefinition>
</html>
