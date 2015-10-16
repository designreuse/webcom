package br.com.ftech.webcom.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ftech.webcom.dto.CupomVendaDTO;
import br.com.ftech.webcom.dto.ItemVendaDTO;
import br.com.ftech.webcom.entity.Cliente;
import br.com.ftech.webcom.entity.ItensVenda;
import br.com.ftech.webcom.entity.Produto;
import br.com.ftech.webcom.entity.Usuario;
import br.com.ftech.webcom.entity.VendaCabecalho;
import br.com.ftech.webcom.service.ClienteService;
import br.com.ftech.webcom.service.ItensVendaService;
import br.com.ftech.webcom.service.ProdutoService;
import br.com.ftech.webcom.service.UsuarioService;
import br.com.ftech.webcom.service.VendaCabecalhoService;

@Controller
@Transactional
@RequestMapping("/protected/venda")
public class VendaController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private VendaCabecalhoService vendaCabService;
	
	@Autowired
	private ItensVendaService itensVendaService;


	@RequestMapping(value = "/abrir-venda.do")
	public String abrirVenda() {
		return "venda/pdv";
	}

	@RequestMapping(value = "/adicionarVendaCabecalho.do")
	@ResponseBody
	public Long abrirVenda(@RequestParam("id_cliente") Long id_cliente, @RequestParam("status_venda") String status_venda) {
		VendaCabecalho vendaCab = new VendaCabecalho();

		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.buscaPorLogin(login);
		vendaCab.setUsuario(usuario);
		vendaCab.setStatusVenda(status_venda);
		vendaCab.setDataHoraVenda(new DateTime().toDate());
		if (id_cliente != null) {
			Cliente cliente = clienteService.findById(id_cliente);
			vendaCab.setCliente(cliente);
		}
		vendaCabService.save(vendaCab);
		return vendaCab.getId_venda_cabecalho();

	}
	
	@RequestMapping(value = "/adicionar-venda.do")
	@ResponseBody
	public Long abrirVenda( @RequestParam("status_venda") String status_venda) {
		VendaCabecalho vendaCab = new VendaCabecalho();

		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.buscaPorLogin(login);
		vendaCab.setUsuario(usuario);
		vendaCab.setStatusVenda(status_venda);
		vendaCab.setDataHoraVenda(new DateTime().toDate());
		vendaCab.setCliente(null);
		vendaCabService.save(vendaCab);
		return vendaCab.getId_venda_cabecalho();

	}
	
	
	@RequestMapping(value = "/update-venda.do")
	@ResponseBody
	public Long encerrarVenda(@RequestParam("id_venda_cabecalho") Long id_venda, @RequestParam("qtde_itens")Integer qtde_itens,
			@RequestParam("valor_total") String valorTotal, @RequestParam("status_venda") String status_venda) {
		
		VendaCabecalho vendaCab = new VendaCabecalho();
		vendaCab.setId(id_venda);
		vendaCab.setValorTotal(new BigDecimal(valorTotal));
		vendaCab.setQtdeItens(qtde_itens);
		vendaCab.setStatusVenda(status_venda);	
	
		vendaCabService.update(vendaCab);
		
		VendaCabecalho venda = vendaCabService.findById(id_venda);
		
		//gerando nota
		CupomVendaDTO cv = new CupomVendaDTO();
		cv.setData(venda.getDataHoraVenda());
		cv.setVlTotal(venda.getValorTotal());	
		List<ItensVenda> itensVenda = itensVendaService.buscaPorVenda(venda);
		List<ItemVendaDTO> lista = new ArrayList<>();
		for(ItensVenda iv : itensVenda){
			if(iv.getStatusItem().equals("vendido")){
				ItemVendaDTO ivdto = new ItemVendaDTO(iv);
				lista.add(ivdto);
			}
		}
		cv.setLista(lista);
		
		for(ItemVendaDTO ivdt : cv.getLista()){
			System.out.println(ivdt.getNome_produto());
			System.out.println(ivdt.getQtde().toString());
			System.out.println(ivdt.getSequencia().toString());
			System.out.println(ivdt.getValor_unitario().toString());
			System.out.println(ivdt.getSub_total().toString());
		}
	//	PrinterMatrixText printer = new PrinterMatrixText();
	//	printer.imprimirNota(cv);
		
	
		
		for(ItensVenda itens:itensVenda ){
			Produto produto = produtoService.findById(itens.getProduto().getId_produto());
			Double qt = produto.getQtde_estoque();
			produto.setQtde_estoque(qt - itens.getQuantidade());
			produtoService.updateEstoque(produto);
		}
		
		
		
		return vendaCab.getId_venda_cabecalho();

	}
	
	
	
}
