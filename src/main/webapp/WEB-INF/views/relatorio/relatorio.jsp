<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="corpo">

		<div class="container">
			<fieldset>
  				<legend>Relatório de Vendas - escolha o período</legend>
  				
		<form method="post"
					action="${pageContext.request.contextPath}/protected/relatorio/gerar-pdf.do"
					class="form-inline" role="form"  >

					<div class="form-group ">
						<label for="dataInicio">Data Inicial:</label> 
						<input type="text" maxlength="10" placeholder="dd/mm/aaaa" 	autofocus  
						id="dataInicio"  name="dataInicio" 
							class="form-control" />
					</div>
					

					<div class="form-group">
						<label for="dataFim">Data Final:</label> 
						<input type="text" maxlength="10" placeholder="dd/mm/aaaa" 
						id="dataFim"	name="dataFim"  class="form-control" />
					</div>

					<button type="submit" id="btn-submit" class="btn btn-default"
						disabled>Gerar relatório</button>


			</form>
			
			</fieldset>
		</div>
		
		
		<script type="text/javascript">
			$(document).ready(function() {
				carregarNoLoadPaginaRelatorio();
			});
		</script>
		
		
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
