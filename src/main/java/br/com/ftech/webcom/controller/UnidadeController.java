package br.com.ftech.webcom.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ftech.webcom.entity.Unidade;
import br.com.ftech.webcom.service.UnidadeService;
import br.com.ftech.webcom.util.Mensagem;
import br.com.ftech.webcom.util.Mensagem.TipoMensagem;

@Controller
@RequestMapping("/protected/unidade")
public class UnidadeController {

	@Autowired
	private UnidadeService unidadeService;

	@RequestMapping(value = "/preparaCadastroUnidade.do")
	public String redirecionaCadastroUnidade(Map<String, Object> map) {
		map.put("unidade", new Unidade());
		return "unidade/cadastroUnidade";
	}

	@RequestMapping(value = "/cadastrar.do", method = RequestMethod.POST)
	public String cadastrar(Unidade unidade, Model model, HttpServletRequest request) {

		unidadeService.save(unidade);
	
		return "redirect:/protected/unidade/listar.do";
	}
	
	@RequestMapping(value = "/listar.do", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Unidade> unidades = unidadeService.findAll();
		model.addAttribute("unidades", unidades);
		return "unidade/listaUnidades";
	}

	@RequestMapping(value = "/excluir.do", method = RequestMethod.GET)
	public String excluir(Long idUnidade, Model model) {
		unidadeService.delete(idUnidade);
		model.addAttribute("mensagem", new Mensagem("Sucesso ao excluir a unidade", TipoMensagem.SUCESSO));
		return "forward:/protected/unidade/listar.do";
	}

	@RequestMapping(value = "/preparaEdit.do", method = RequestMethod.GET)
	public String editPage(Long idUnidade, Model model) {
		Unidade unidade = unidadeService.findById(idUnidade);
		model.addAttribute("unidade", unidade);
		return "unidade/editaUnidade";
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String editarUnidade(Unidade unidade, Model model, HttpServletRequest request) {
		unidadeService.update(unidade);
		model.addAttribute("mensagem", new Mensagem("Sucesso ao editar a unidade", TipoMensagem.SUCESSO));
		return "redirect:/protected/unidade/listar.do";
	}

}
