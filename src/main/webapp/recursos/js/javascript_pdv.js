function carregarNoLoadPagina(){ 
    
    //Carregar lista de clientes
    carregarClientesSelect();
    
    //escutar click botão para iniciar uma venda
    $('#btn_iniciar_venda').click(function() {
     //   if($("#select_cliente").val() === "0"){
       //     alert("Selecione um cliente antes de iniciar uma venda.");
     //   }else{
            iniciarNovaVenda();
            $("#codigo_produto").focus();

    //    }
    });
    
    
        //Escutar evento perda de foco para mostrar os dados do produto
    $("#codigo_produto").focusout(function(){
        if($("#codigo_produto").val() !== "" ){
            buscaProdutoPorId($("#codigo_produto").val());
            $("#btnAdicionarItem").focus();
     //   }else{
    //        alert("Valor informar o código do produto válido");
        }    
    });
    
    //Escutar evento perda de foco para calcular o total do item
    $("#qtde_item").focusout(function(){
   //     if($("#qtde_item").val() !== "" && $("#qtde_item").val() > 0){
            var valorTotalitem = parseFloat($("#qtde_item").val()) *  parseFloat($("#preco_unitario").val());            
            $("#valor_total_item").val(valorTotalitem.toString());            
      //  }else{
        //    alert("Valor informar a quantidade vendida válida.");
     //   }
    }); 
    
        //Escuta o evento click para adicionar o item ao cupom
    $("#btnAdicionarItem").click(function(){
        if($("#qtde_item").val() !== "" && $("#qtde_item").val() > 0  ){
                       
            var statusItem = "vendido";
            adicionarItemCupom($("#codigo_produto").val(), $("#id_venda_cabecalho").text(), $(contadorSeqItemVenda).val(), $("#nomeProduto").text(), $("#qtde_item").val(), $("#preco_unitario").val(), $("#valor_total_item").val(), statusItem);            
            $("#codigo_produto").focus();
        }else{
            alert("Valor informar o código do produto e uma quantidade válida.");
        }
    });
    
}
	
//Fim carregarNoLoadPagina()-----------------------------------------

function carregarClientesSelect(){			
        $(function(){
            $.getJSON("/webcom/protected/cliente/getClientes.do", function(j){
                var options = "<option value='0'>Selecione um cliente</option>";
                for (var i = 0; i < j.length; i++) {
                options += '<option value="' + j[i].id_cliente + '">' + j[i].nome + '</option>';
                }
                $("select#select_cliente").html(options);
            });	
        }); 
}

function iniciarNovaVenda(){
    $("#btn_iniciar_venda").attr("disabled", "disabled");
    $("#select_cliente").attr("disabled","disabled");
    $("#btn_encerrar_venda").removeAttr("disabled");    
    $("#status_venda").text("Em aberto");         
    $("#div_venda_itens").show();
    
    if($("#select_cliente").val() !== "0"){
    var cliente_id = $("#select_cliente").val();
           $(function(){
           //Busca os dados do cliente selecionado
           $.getJSON("/webcom/protected/cliente/"+cliente_id+"/getCliente.do", function(cliente){
               $("#cliente_nome").text(cliente.nome);
               $("#codigo_cliente").text(cliente.id_cliente);                
                
                //Adicionar a abertura de uma nova venda no banco
                $.ajax({
                    type: "POST",
                    url: "/webcom/protected/venda/adicionarVendaCabecalho.do",
                    dataType: "html",
                    data:  {"id_cliente": cliente_id, "status_venda": $("#status_venda").text()},
            success: function(retornoIdVendaCab){
                    $("#id_venda_cabecalho").text(retornoIdVendaCab); 
                    alert("Aberta uma nova venda de código: " + retornoIdVendaCab + " para o cliente: " + cliente.nome);
                    }
                });
            });	
        }); 
    }else{
    	 $.ajax({
             type: "POST",
             url: "/webcom/protected/venda/adicionar-venda.do",
             dataType: "html",
             data:  {"status_venda": $("#status_venda").text()},
     success: function(retornoIdVendaCab){
             $("#id_venda_cabecalho").text(retornoIdVendaCab); 
             alert("Aberta uma nova venda de código: " + retornoIdVendaCab );
             }
         });
    }
}


function buscaProdutoPorId(id_produto){
    $.getJSON("/webcom/protected/produto/" + id_produto+"/getProduto.do", function(produto){
        $("#preco_unitario").val(produto.preco_venda);
        $("#qtde_item").val(1);
        $("#nomeProduto").text(produto.nome);
        $("#img_produto").attr("src", produto.imagem_produto);
        var valorTotalitem = parseFloat($("#qtde_item").val()) *  parseFloat($("#preco_unitario").val());            
        $("#valor_total_item").val(valorTotalitem.toString()); 
  
    });
}

function adicionarItemCupom(id_produto,id_venda_cabecalho, seqItemVenda, nome_produto, qtde_item, preco_unitario, valor_total_item, statusItem){
                var codigo_vend = parseFloat(id_venda_cabecalho.replace('"',''));   
                //Adicionar item vendido no banco de dados
                $.ajax({
                    type: "POST",
                    url: "/webcom/protected/item-venda/adicionar-item-venda.do",
                    dataType: "html",
                    data:  {"id_produto": id_produto, "id_venda_cabecalho": id_venda_cabecalho, "sequencia": seqItemVenda, "nome_produto": nome_produto, "qtde": qtde_item, "valor_unitario": preco_unitario, "sub_total": valor_total_item, "status_item": statusItem},
            success: function(retornoIdItemVenda){
                
                    //Incrementa e seta + 1 na sequencia de itens
                    var seq = parseInt(seqItemVenda) + 1;
                    $("#contadorSeqItemVenda").val(seq);
                    
                    //Preenchar a tabela com os itens vendidos
                    $.getJSON("/webcom/protected/item-venda/"+codigo_vend+"/get-itens-venda.do", function(itens){
                        
                    	 //Definir cabelha tabela
                        var linhasTabela = '<tr><th>ITEM</th> <th>CÓDIGO</th> <th>DESCRIÇÃO</th> <th>QDTE</th> <th>PREÇO</th> <th>TOTAL</th> <th>STATUS</th><th></th> </tr>';
                        
                        var totalGeralVenda = 0.0; //Armazenar o total geral
                            
                        for (var i = 0; i < itens.length; i++) {                     
                                linhasTabela += "<tr><th>"+ itens[i].sequencia +"</th><th>"+ itens[i].id_produto +"</th><th>"+ itens[i].nome_produto +"</th><th>"+ itens[i].qtde +"</th><th>"+ itens[i].valor_unitario +"</th><th>"+ itens[i].sub_total +"</th><th>"+ itens[i].status_item +"</th><th><button class='glyphicon glyphicon-remove' aria-hidden='true' onClick='cancelarItemVenda("+ itens[i].id_itens_venda +","+ itens[i].sequencia + ","+ itens[i].id_venda_cabecalho +")'/></th></tr>";
                            
                            //Calcula o total geral da venda
                            if(itens[i].status_item === "vendido"){
                                totalGeralVenda += parseFloat(itens[i].sub_total);
                            }
                            
                            //Exibir tabela e setar total geral
                            $("table#tabela_cupom").html(linhasTabela);
                            $("#total_geral").text("R$ " + totalGeralVenda.toString());                                              
                        }
                        }); 
                    
                        //Limpa os campos 
                        $("#codigo_produto").val("");
                        $("#qtde_item").val("");
                        $("#preco_unitario").val("");
                        $("#valor_total_item").val("");
                        $("#nomeProduto").text("");
                        $("#img_produto").attr("src", "");

                    }});    
}

function cancelarItemVenda(id_itens_venda, sequencia, id_venda_cabecalho){
    $.ajax({
        type: "POST",
        url: "/webcom/protected/item-venda/update-item-venda.do",
        dataType: "html",
        data:  {"id_itens_venda": id_itens_venda},
        success: function(retornoIdItemCancelado){ 
            
                //Preenchar a tabela com os itens vendidos
                $.getJSON("/webcom/protected/item-venda/"+id_venda_cabecalho+"/get-itens-venda.do", function(itens){
           
                	 //Definir cabelha tabela
                    linhasTabela = '<tr><th>ITEM</th> <th>CÓDIGO</th> <th>DESCRIÇÃO</th> <th>QDTE</th> <th>PREÇO</th> <th>TOTAL</th> <th>STATUS</th><th></th> </tr>';
                    var totalGeralVenda = 0.0;
                            
                    for (var i = 0; i < itens.length; i++) {                     
                        linhasTabela += "<tr><th>"+ itens[i].sequencia +"</th><th>"+ itens[i].id_produto +"</th><th>"+ itens[i].nome_produto +"</th><th>"+ itens[i].qtde +"</th><th>"+ itens[i].valor_unitario +"</th><th>"+ itens[i].sub_total +"</th><th>"+ itens[i].status_item +"</th><th><button class='glyphicon glyphicon-remove' aria-hidden='true' onClick='cancelarItemVenda("+ itens[i].id_itens_venda +","+ itens[i].sequencia +")'/></th></tr>";
                    //Calcula o total geral da venda
                    if(itens[i].status_item === "vendido"){
                        totalGeralVenda += parseFloat(itens[i].sub_total);
                    }                        
                 }                    
                    //Exibir tabela e setar total geral
                    $("table#tabela_cupom").html(linhasTabela);
                    $("#total_geral").text("R$ " + totalGeralVenda.toString());                        
                });             
                alert("O item: " + sequencia + " foi cancelado.");    
                $("#codigo_produto").focus();
            }
        });
}



function encerrarVenda(){
     //Recuperar dados para finalizar venda
     var id_venda = $("#id_venda_cabecalho").text();
     var id_venda = parseInt(id_venda);
     var qtde_itens_vendidos = $("#contadorSeqItemVenda").val()-1;
     var total_geral_venda = $("#total_geral").text().replace('R$ ','');
    
     
     //Alterar a tabela venda cabeçalho para finalizada    
     $.ajax({
        type: "POST",
        url: "/webcom/protected/venda/update-venda.do",
        dataType: "html",
        data:  {"id_venda_cabecalho": id_venda, "qtde_itens": qtde_itens_vendidos, "valor_total": total_geral_venda, "status_venda": "Finalizada"},
        success: function(){
            alert("Venda finalizada - Total: " + $("#total_geral").text());     
            $("table#tabela_cupom").html();
            $("#total_geral").text("");   
            $("#btn_iniciar_venda").removeAttr("disabled");  
            $("#select_cliente").removeAttr("disabled");  
            $("#btn_encerrar_venda").attr("disabled", "disabled");    
            $("#status_venda").text("Finalizada");         
            $("#div_venda_itens").hide();
            $("#cliente_nome").text("");
            $("#codigo_cliente").text(""); 
            $("#contadorSeqItemVenda").val(1);
            $("#id_venda_cabecalho").text("");       
        }     
     });
    
}