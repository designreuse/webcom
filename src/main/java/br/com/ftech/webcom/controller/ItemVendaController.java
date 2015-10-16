package br.com.ftech.webcom.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ftech.webcom.dto.ItemVendaDTO;
import br.com.ftech.webcom.entity.ItensVenda;
import br.com.ftech.webcom.entity.Produto;
import br.com.ftech.webcom.entity.VendaCabecalho;
import br.com.ftech.webcom.service.ClienteService;
import br.com.ftech.webcom.service.ItensVendaService;
import br.com.ftech.webcom.service.ProdutoService;
import br.com.ftech.webcom.service.UsuarioService;
import br.com.ftech.webcom.service.VendaCabecalhoService;

@Controller
@RequestMapping("/protected/item-venda")
public class ItemVendaController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private VendaCabecalhoService vendaCabService;

	@Autowired
	private ItensVendaService itensVendaService;

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = "/adicionar-item-venda.do")
	@ResponseBody
	public Long adicionarItensVenda(@RequestParam("id_produto") String id_produto, @RequestParam("id_venda_cabecalho") Long codigo_venda,
			@RequestParam("sequencia") Integer seqItemVenda, @RequestParam("nome_produto") String nome_produto,
			@RequestParam("qtde") Integer qtde, @RequestParam("valor_unitario") String preco_unitario,
			@RequestParam("sub_total") String valor_total_item, @RequestParam("status_item") String status_item) {

		Produto produto = produtoService.findById(id_produto);
		VendaCabecalho vendaCabecalho = vendaCabService.findById(codigo_venda);
		ItensVenda itensVenda = new ItensVenda();
		itensVenda.setProduto(produto);
		itensVenda.setVendaCabecalho(vendaCabecalho);
		itensVenda.setSequencia(seqItemVenda);
		itensVenda.setNomeProduto(nome_produto);
		itensVenda.setQuantidade(qtde);
		itensVenda.setValorUnitario(new BigDecimal(preco_unitario));
		itensVenda.setSubTotal(new BigDecimal(valor_total_item));
		itensVenda.setStatusItem(status_item);

		itensVendaService.save(itensVenda);
		return itensVenda.getId_itens_venda();

	}

	@RequestMapping(value = "/{id}/get-itens-venda.do", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemVendaDTO> getItensVenda(@PathVariable("id") Long id_venda) {

		VendaCabecalho venda = vendaCabService.findById(id_venda);
		List<ItensVenda> lista = itensVendaService.buscaPorVenda(venda);
		List<ItemVendaDTO> listaDTOs = new ArrayList<>();

		for (ItensVenda iv : lista) {
			listaDTOs.add(new ItemVendaDTO(iv));

		}

		return listaDTOs;
	}

	@RequestMapping(value = "/update-item-venda.do")
	@ResponseBody
	public Long alterarStatusItensVenda(@RequestParam("id_itens_venda") Long id_itens_venda) {
		System.out.println(id_itens_venda + "no controller");
		ItensVenda itemVenda = new ItensVenda();
		itemVenda.setId_itens_venda(id_itens_venda);
		itemVenda.setStatusItem("cancelado");
		itensVendaService.updateStatus(itemVenda);
		return itemVenda.getId_itens_venda();

	}

}
