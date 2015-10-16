<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="springform"
	uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html >
<html>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="corpo">

		<script type="text/javascript">
			var $j = jQuery.noConflict();
			// Use jQuery com a variavel $j para evitar conflitos entre plugins
			$j(document).ready(function() {
				$j("#rg").mask("9999999999999999999999999999999999"); // onde #data é o id do campo
				$j("#cep").mask("99.999-999"); // onde #cep é o id do campo
				$j("#cpf").mask("999.999.999-99"); // onde #cpf é o id do campo
				$j("#cnpj").mask("99.999.999/9999-99"); // onde #cnpj é o id do campo
				$j("#telefone").mask("(99) 9999-9999"); // onde #telefone é o id do campo
			});
		</script>


		<h1>Novo Cliente</h1>

		<springform:form method="post"
			action="${pageContext.request.contextPath}/protected/cliente/cadastrar.do"
			modelAttribute="cliente" class="form-horizontal">


			<div class="form-group">
				<label class="col-md-1 control-label" for="nome">Nome:</label>
				<div class="col-md-4">
					<springform:input id="nome" path="nome" cssClass="form-control"
						placeholder="Nome" autofocus="true" required="true" maxlength="48" />
				</div>
			</div>
			
		

			<div class="form-group">
				<label class="col-md-1 control-label" for="cpf">CPF</label>
				<div class="col-md-4">
					<springform:input type="text" id="cpf" path="cpf"
						cssClass="form-control" placeholder="CPF" maxlength="30" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="cnpj">CNPJ</label>
				<div class="col-md-4">
					<springform:input type="text" id="cnpj" path="cnpj"
						cssClass="form-control" placeholder="CNPJ" maxlength="30" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="rg">RG</label>
				<div class="col-md-4">
					<springform:input type="text" id="rg"  path="rg"
						cssClass="form-control" placeholder="RG"  />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="endereco">Endereço</label>
				<div class="col-md-4">
					<springform:input id="endereco" path="endereco"
						cssClass="form-control" placeholder="Endereço" maxlength="48" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="bairro">Bairro</label>
				<div class="col-md-4">
					<springform:input id="bairro" path="bairro" cssClass="form-control"
						placeholder="Bairro" maxlength="48" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="cidade">Cidade</label>
				<div class="col-md-4">
					<springform:input id="cidade" path="cidade" cssClass="form-control"
						placeholder="Cidade" maxlength="48" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="estado">Estado</label>
				<div class="col-md-4">
					<springform:select id="estado" path="estado"
						cssClass="form-control">
						<springform:option value="Acre">Acre</springform:option>
						<springform:option value="Alagoas">Alagoas</springform:option>
						<springform:option value="Amazonas">Amazonas</springform:option>
						<springform:option value="Bahia">Bahia</springform:option>
						<springform:option value="Ceará">Ceará</springform:option>
						<springform:option value="Distrito Federal">Distrito Federal</springform:option>
						<springform:option value="Espírito Santo">Espírito Santo</springform:option>
						<springform:option value="Goiás">Goiás</springform:option>
						<springform:option value="Maranhão">Maranhão</springform:option>
						<springform:option value="Mato Grosso">Mato Grosso</springform:option>
						<springform:option value="Mato Grosso do Sul">Mato Grosso do Sul</springform:option>
						<springform:option value="Minas Gerais">Minas Gerais</springform:option>
						<springform:option value="Pará">Pará</springform:option>
						<springform:option value="Paraíba">Paraíba</springform:option>
						<springform:option value="Paraná">Paraná</springform:option>
						<springform:option value="Pernambuco">Pernambuco</springform:option>
						<springform:option value="Piauí">Piauí</springform:option>
						<springform:option value="Rio de Janeiro">Rio de Janeiro</springform:option>
						<springform:option value="Rio Grande do Norte">Rio Grande do Norte</springform:option>
						<springform:option value="Rio Grande do Sul">Rio Grande do Sul</springform:option>
						<springform:option value="Rondônia">Rondônia</springform:option>
						<springform:option value="Roraima">Roraima</springform:option>
						<springform:option value="Santa Catarina">Santa Catarina</springform:option>
						<springform:option selected="selected" value="São Paulo">São Paulo</springform:option>
						<springform:option value="Sergipe">Sergipe</springform:option>
						<springform:option value="Tocantins">Tocantins</springform:option>
					</springform:select>
				</div>
			</div>


			<div class="form-group">
				<label class="col-md-1 control-label" for="telefone">Telefone</label>
				<div class="col-md-4">
					<springform:input type="text" id="telefone" path="telefone"
						cssClass="form-control" placeholder="Telefone" maxlength="48" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="email">Email</label>
				<div class="col-md-4">
					<springform:input type="email" id="email" path="email"
						cssClass="form-control" placeholder="Email" maxlength="48" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-1 control-label" for="observacao">Observação:</label>
				<div class="col-md-4">
					<springform:textarea rows="3" cols="30" path="observacao" 
						cssClass="form-control" id="observacao"  maxlength="200" />
				</div>
			</div>
			

			<input type="submit" value="Salvar" class="btn btn-primary" />


			<a
				href="${pageContext.request.contextPath}/protected/cliente/listar.do">
				<input type="button" value="Cancelar" class="btn btn-default" />
			</a>
		</springform:form>





	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
