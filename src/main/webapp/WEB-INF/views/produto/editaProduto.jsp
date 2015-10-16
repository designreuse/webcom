<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="corpo">



		<h1>Editar Produto</h1>

		<springform:form method="post"
			action="${pageContext.request.contextPath}/protected/produto/edit.do"
			modelAttribute="produto" class="form-horizontal"  enctype="multipart/form-data">


			<div class="form-group">
				<label class="col-md-1 control-label" for="id_produto">Código
					do produto:</label>
				<div class="col-md-4">
					<springform:input id="id_produto" path="id_produto"
						cssClass="form-control" disabled="true"
						placeholder="Código do produto" maxlength="20" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="nome">Nome:</label>
				<div class="col-md-4">
					<springform:input id="nome" path="nome" cssClass="form-control"
						placeholder="Nome" autofocus="true" required="true" maxlength="48" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="descricao">Descrição</label>
				<div class="col-md-4">
					<springform:input id="descricao" path="descricao"
						cssClass="form-control" placeholder="Descrição" required="true"
						maxlength="48" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="preco_venda">Preço
					de venda</label>
				<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-addon">R$</span>

						<springform:input type="number" step="any" id="preco_venda"
							path="preco_venda" cssClass="form-control"
							placeholder="Preço de venda" maxlength="48" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="preco_compra">Preco
					de compra</label>
				<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-addon">R$</span>

						<springform:input type="number" step="any" id="preco_compra"
							path="preco_compra" cssClass="form-control"
							placeholder="Preço de compra" maxlength="48" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-1 control-label" for="qtde_estoque">Quantidade
				</label>
				<div class="col-md-4">
					<springform:input type="number" step="any" id="qtde_estoque"
						path="qtde_estoque" cssClass="form-control"
						placeholder="Quantidade" maxlength="48" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-1 control-label" for="fornecedor">Fornecedor:</label>
				<div class="col-md-4">
					<select id="fornecedor" name="nomeFornecedor" class="form-control">
						<option value="">selecione</option>
						<c:forEach items="${fornecedores}" var="f">
							<option value="${f.nome}">${f.nome}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="unidade">Unidade:</label>
				<div class="col-md-4">
					<select id="unidade" name="nomeUnidade" class="form-control">
						<option value="">selecione</option>
						<c:forEach items="${unidades}" var="u">
							<option value="${u.descricao}">${u.descricao}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-1 control-label" for="imagem">Imagem:</label>
				<div class="col-md-4">
				 <input type="file" name="fileUpload" />
				 </div>
			</div>
			


			<springform:hidden path="id_produto" />



			<input type="submit" value="Salvar" class="btn btn-primary" />

			<a
				href="${pageContext.request.contextPath}/protected/produto/listar.do">
				<input type="button" value="Cancelar" class="btn btn-default" />
			</a>
		</springform:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>