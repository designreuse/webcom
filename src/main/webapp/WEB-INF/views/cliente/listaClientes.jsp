<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="corpo">

		


			<h1>
				Cadastro de Clientes 
			</h1>
			
			<form method="get"	action="${pageContext.request.contextPath}/protected/cliente/busca-nome.do"	class="form-inline col-md-6" role="form">
				<div class="form-group  ">
					<label class="control-label" for="">Pesquisar clientes:</label>
					<input type="text" class="form-control" name="nome" autofocus="true" placeholder="Nome do cliente">
				</div>
				<button type="submit" class="btn btn-default  ">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</form>
			
				<a	href="${pageContext.request.contextPath}/protected/preparaCadastroCliente.do">
					<input type="button" value="Adicionar cliente"	class="btn btn-default  pull-right" />
				</a>
			
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Nome</th>
						<th>CPF</th>
						<th>CNPJ</th>
						<th>RG</th>
						<th>Endereço</th>
						<th>Bairro</th>
						<th>Cidade</th>
						<th>Estado</th>
						<th>Telefone</th>
						<th>Email</th>
						<th>Observação</th>
						<th>Data do cadastro</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${clientes}">
						<tr>
							<td>${c.nome}</td>
							<td>${c.cpf}</td>
							<td>${c.cnpj}</td>
							<td>${c.rg}</td>
							<td>${c.endereco}</td>
							<td>${c.bairro}</td>
							<td>${c.cidade}</td>
							<td>${c.estado}</td>
							<td>${c.telefone}</td>
							<td>${c.email}</td>
							<td>${c.observacao}</td>
							<td>${c.data_cadastro}</td>
							<td><a
								href="${pageContext.request.contextPath}/protected/cliente/preparaEdit.do?idCliente=${c.id_cliente}">
									<span class="glyphicon glyphicon-edit"></span>

							</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
								href="${pageContext.request.contextPath}/protected/cliente/excluir.do?idCliente=${c.id_cliente}">
									<span class="glyphicon glyphicon-trash"></span>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			



			

	

	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
