package br.com.ftech.webcom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ftech.webcom.entity.Fornecedor;
import br.com.ftech.webcom.service.FornecedorService;
import br.com.ftech.webcom.util.Mensagem;
import br.com.ftech.webcom.util.Mensagem.TipoMensagem;

@Controller
@RequestMapping("/protected/fornecedor")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;

	@RequestMapping(value = "/cadastrar.do", method = RequestMethod.POST)
	public String cadastrar(Fornecedor fornecedor, Model model) {
		fornecedorService.save(fornecedor);
		model.addAttribute("fornecedor", new Fornecedor());
		model.addAttribute("mensagem", new Mensagem("Sucesso ao cadastrar o fornecedor", TipoMensagem.SUCESSO));
		return "redirect:/protected/fornecedor/listar.do";
	}

	@RequestMapping(value = "/busca-nome.do", method = RequestMethod.GET)
	public String buscaPorNome(String nome, Model model) {
		List<Fornecedor> fornecedores = fornecedorService.findByName(nome);
		model.addAttribute("fornecedores", fornecedores);
		return "fornecedor/listaFornecedores";
	}

	@RequestMapping(value = "/listar.do", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Fornecedor> fornecedores = fornecedorService.findAll();
		model.addAttribute("fornecedores", fornecedores);
		return "fornecedor/listaFornecedores";
	}

	@RequestMapping(value = "/excluir.do", method = RequestMethod.GET)
	public String excluir(Long idFornecedor, Model model) {
		fornecedorService.delete(idFornecedor);
		model.addAttribute("mensagem", new Mensagem("Sucesso ao excluir o fornecedor", TipoMensagem.SUCESSO));
		return "forward:/protected/fornecedor/listar.do";
	}

	@RequestMapping(value = "/preparaEdit.do", method = RequestMethod.GET)
	public String editPage(Long idFornecedor, Model model) {
		Fornecedor fornecedor = fornecedorService.findById(idFornecedor);
		model.addAttribute("fornecedor", fornecedor);
		return "fornecedor/editaFornecedor";
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String editarFornecedor(Fornecedor fornecedor, Model model) {
		fornecedorService.update(fornecedor);
		model.addAttribute("mensagem", new Mensagem("Sucesso ao editar o fornecedor", TipoMensagem.SUCESSO));
		return "redirect:/protected/fornecedor/listar.do";
	}


	
}
