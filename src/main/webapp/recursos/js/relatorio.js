function carregarNoLoadPaginaRelatorio() {
	
	$("#dataInicio").mask("99/99/9999");
	$("#dataFim").mask("99/99/9999");
	
	var datainicial = document.getElementById('dataInicio');
	var datafinal = document.getElementById('dataFim');

	function gerarData(str) {
	    var partes = str.split("/");
	    return new Date(partes[2], partes[1] - 1, partes[0]);
	}
    $("#dataFim").focusout(function(){
    	 var inicio = datainicial.value;
 	    var fim = datafinal.value;
 	    if (inicio.length != 10 || fim.length != 10) return;

 	    if (gerarData(fim) < gerarData(inicio)){
 	    	alert("A data final deve ser maior que a data inicial");
 	    }else{
 	    	$("#btn-submit").removeAttr('disabled');
 	    }
 	    	
 	    	
    });

	
}
