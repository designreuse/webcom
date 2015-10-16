package br.com.ftech.webcom.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ftech.webcom.entity.Role;
import br.com.ftech.webcom.entity.Usuario;
import br.com.ftech.webcom.service.RoleService;
import br.com.ftech.webcom.service.UsuarioService;
import br.com.ftech.webcom.util.FuncoesHash;
import br.com.ftech.webcom.util.Mensagem;
import br.com.ftech.webcom.util.Mensagem.TipoMensagem;

@Controller
@RequestMapping("/protected/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/preparaCadastroUsuario.do")
	public String redirecionaCadastroUsuario(Map<String, Object> map,Model model) {
		map.put("usuario", new Usuario());
		map.put("roles", new HashSet<Role>());

		return "usuario/cadastroUsuario";
	}

	@RequestMapping(value = "/cadastrar.do", method = RequestMethod.POST)
	public String cadastrar(Usuario usuario, Model model, HttpServletRequest request) {

		Usuario usuarioBuscado = usuarioService.buscaPorLogin(usuario.getLogin());
		if (usuarioBuscado != null) {
			model.addAttribute("mensagem", new Mensagem("Login indisponível, tente outro!", TipoMensagem.ERRO));
			Map<String, Object> map = new HashMap<>();
			map.put("usuario", usuario);
			return redirecionaCadastroUsuario(map,model);
		}

		usuario.setSenha(FuncoesHash.md5(usuario.getSenha()));
		usuarioService.cadastrar(usuario);
		Usuario usuarioCadastrado = usuarioService.buscaPorLogin(usuario.getLogin());
		String permissao = request.getParameter("permissao");
		Role role = new Role();
		role.setRole(permissao);
		role.setUsuario(usuarioCadastrado);
		roleService.save(role);

		return "redirect:/protected/usuario/listar.do";
	}

	@RequestMapping(value = "/busca-nome.do", method = RequestMethod.GET)
	public String buscaPorNome(String nome, Model model) {
		List<Usuario> usuarios = usuarioService.findByName(nome);
		model.addAttribute("usuarios", usuarios);
		return "usuario/listaUsuarios";
	}

	@RequestMapping(value = "/listar.do", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Usuario> usuarios = usuarioService.listar();
		model.addAttribute("usuarios", usuarios);
		return "usuario/listaUsuarios";
	}

	@RequestMapping(value = "/excluir.do", method = RequestMethod.GET)
	public String excluir(Long idUsuario, Model model) {
		usuarioService.remover(idUsuario);
		model.addAttribute("mensagem", new Mensagem("Sucesso ao excluir o usuário", TipoMensagem.SUCESSO));
		return "forward:/protected/usuario/listar.do";
	}

	@RequestMapping(value = "/preparaEdit.do", method = RequestMethod.GET)
	public String editPage(Long idUsuario, Model model) {
		Usuario usuario = usuarioService.findById(idUsuario);
		model.addAttribute("usuario", usuario);
		return "usuario/editaUsuario";
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String editarUsuario(Usuario usuario, Model model, HttpServletRequest request) {
		
		Usuario usuarioBuscado = usuarioService.buscaPorLogin(usuario.getLogin());
		if (usuarioBuscado != null) {
			model.addAttribute("mensagem", new Mensagem("Login indisponível, tente outro!", TipoMensagem.ERRO));
			return editPage(usuario.getId_usuario(),model);
		}
		
		
		if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
			usuario.setSenha(FuncoesHash.md5(usuario.getSenha()));
		}

		usuarioService.atualizar(usuario);
		String permissao = request.getParameter("permissao");
		List<Role> roles = roleService.buscaPermissoesPorUsuario(usuario);
		for (Role r : roles) {
			if (!r.getRole().equals(permissao)) {
				Role role = new Role();
				role.setRole(permissao);
				role.setUsuario(usuario);
				roleService.save(role);
			}
		}

		model.addAttribute("mensagem", new Mensagem("Sucesso ao editar o usuário", TipoMensagem.SUCESSO));
		return "redirect:/protected/usuario/listar.do";
	}

}
