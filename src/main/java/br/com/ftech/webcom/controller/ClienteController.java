package br.com.ftech.webcom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ftech.webcom.entity.Cliente;
import br.com.ftech.webcom.service.ClienteService;
import br.com.ftech.webcom.util.Mensagem;
import br.com.ftech.webcom.util.Mensagem.TipoMensagem;

@Controller
@RequestMapping("/protected/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/cadastrar.do", method = RequestMethod.POST)
	public String cadastrar(Cliente cliente, Model model) {

		clienteService.save(cliente);
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("mensagem", new Mensagem("Sucesso ao cadastrar o cliente", TipoMensagem.SUCESSO));
		return "redirect:/protected/cliente/listar.do";
	}

	@RequestMapping(value = "/busca-nome.do", method = RequestMethod.GET)
	public String buscaPorNome(String nome, Model model) {
		List<Cliente> clientes = clienteService.findByName(nome);
		model.addAttribute("clientes", clientes);
		return "cliente/listaClientes";
	}

	@RequestMapping(value = "/listar.do", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Cliente> clientes = clienteService.findAll();
		model.addAttribute("clientes", clientes);
		return "cliente/listaClientes";
	}

	@RequestMapping(value = "/excluir.do", method = RequestMethod.GET)
	public String excluir(Long idCliente, Model model) {
		clienteService.delete(idCliente);
		model.addAttribute("mensagem", new Mensagem("Sucesso ao excluir o cliente", TipoMensagem.SUCESSO));
		return "forward:/protected/cliente/listar.do";
	}

	@RequestMapping(value = "/preparaEdit.do", method = RequestMethod.GET)
	public String editPage(Long idCliente, Model model) {
		Cliente cliente = clienteService.findById(idCliente);
		model.addAttribute("cliente", cliente);
		return "cliente/editaCliente";
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String editarCliente(Cliente cliente, Model model) {
		clienteService.update(cliente);
		model.addAttribute("mensagem", new Mensagem("Sucesso ao editar o cliente", TipoMensagem.SUCESSO));
		return "redirect:/protected/cliente/listar.do";
	}

	@RequestMapping(value = "/getClientes.do")
	@ResponseBody
	public List<Cliente> listjson() {
		return clienteService.findAll();
	}

	@RequestMapping(value = "/{id}/getCliente.do", method = RequestMethod.GET)
	@ResponseBody
	public Cliente findById(@PathVariable("id") Long id) {

		return clienteService.findById(id);
	}

}
