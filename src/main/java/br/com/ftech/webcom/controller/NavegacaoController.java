package br.com.ftech.webcom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ftech.webcom.entity.Cliente;
import br.com.ftech.webcom.entity.Fornecedor;
import br.com.ftech.webcom.entity.Produto;
import br.com.ftech.webcom.entity.Unidade;
import br.com.ftech.webcom.service.FornecedorService;
import br.com.ftech.webcom.service.UnidadeService;

@Controller
public class NavegacaoController {
	
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private UnidadeService unidadeService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String redirecionaIndex2(){		
        return  "forward:/protected/home.do";
    }
	
	@RequestMapping(value="/protected/home.do", method = RequestMethod.GET)
    public String redirecionaIndex(){		
        return  "index";
    }
	
	
	@RequestMapping(value="/login.do",method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public String login() {
        return "login";
    }
	
	@RequestMapping(value="/logout.do",method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public String logout() {
        return "login";
    }
	
	@RequestMapping(value="/negado.do",method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public String acessoNegado() {
        return "negado";
    }
	
	@RequestMapping(value="/protected/preparaCadastroProduto.do")
	public String redirecionaCadastroProduto(Map<String, Object> map, Model model) {
		map.put("produto", new Produto());
		List<Fornecedor> fornecedores = fornecedorService.findAll();
		model.addAttribute("fornecedores", fornecedores);
		List<Unidade> unidades = unidadeService.findAll();
		model.addAttribute("unidades", unidades);
		return "produto/cadastroProduto";
	}
	
	
	@RequestMapping(value="/protected/preparaCadastroCliente.do")
	public String redirecionaCadastroCliente(Map<String, Object> map) {
		map.put("cliente", new Cliente());
		return "cliente/cadastroCliente";
	}
	
	
	@RequestMapping(value="/protected/preparaCadastroFornecedor.do")
	public String redirecionaCadastroFornecedor(Map<String, Object> map) {
		map.put("fornecedor", new Fornecedor());
		return "fornecedor/cadastroFornecedor";
	}
	
	
	
}
