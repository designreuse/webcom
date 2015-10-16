package br.com.ftech.webcom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import br.com.ftech.webcom.entity.Fornecedor;
import br.com.ftech.webcom.entity.Produto;
import br.com.ftech.webcom.entity.Unidade;
import br.com.ftech.webcom.repository.ProdutoRepository;
import br.com.ftech.webcom.service.FornecedorService;
import br.com.ftech.webcom.service.ProdutoService;
import br.com.ftech.webcom.service.UnidadeService;
import br.com.ftech.webcom.util.Mensagem;
import br.com.ftech.webcom.util.Mensagem.TipoMensagem;





@Controller
@Transactional
@RequestMapping("/protected/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private UnidadeService unidadeService;

	

	// @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	// public String cadastrar(HttpServletRequest request, HttpServletResponse
	// response, Model model) {
	// Produto produto = new Produto();
	//
	//
	// String nome = request.getParameter("nome");
	// String descricao = request.getParameter("descricao");
	// Double qtde_estoque =
	// Double.valueOf(request.getParameter("qtde_estoque"));
	// DateTime data_cadastro = new DateTime();
	//
	// produto.setNome(nome);
	// produto.setDescricao(descricao);
	// produto.setQtde_estoque(qtde_estoque);
	// produto.setData_cadastro((data_cadastro).toDate());
	//
	//
	// String preco_c = request.getParameter("preco_compra");
	// DecimalFormat dc = (DecimalFormat) DecimalFormat.getCurrencyInstance();
	// dc.setParseBigDecimal(true);
	// BigDecimal valor = null;
	// try {
	// valor = (BigDecimal) dc.parse (preco_c);
	// produto.setPreco_compra(valor);
	//
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	//
	// String preco_v = request.getParameter("preco_venda");
	// try {
	// valor = (BigDecimal) dc.parse (preco_v);
	// produto.setPreco_venda(valor);
	//
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	//
	// produtoService.salvar(produto);
	// model.addAttribute("mensagem", new
	// Mensagem("Sucesso ao cadastrar o produto", TipoMensagem.SUCESSO));
	// return "cadastrarProduto";
	// }

	@RequestMapping(value = "/cadastrar.do", method = RequestMethod.POST)
	public String cadastrar(Produto produto, Model model, String nomeFornecedor, String nomeUnidade,
			@RequestParam CommonsMultipartFile[] fileUpload) {
		
		Produto produtoBuscado = produtoService.findById(produto.getId_produto());
		if(produtoBuscado!=null){
			model.addAttribute("mensagem", new Mensagem("Código existente em outro produto, tente outro código", TipoMensagem.ERRO));
			model.addAttribute("produto",produto);
			return "forward:/protected/preparaCadastroProduto.do";
		}
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {
				produto.setData(aFile.getBytes());
			}
		}
		if (!nomeFornecedor.isEmpty()) {
			List<Fornecedor> fornecedores = fornecedorService.findByName(nomeFornecedor);
			produto.setFornecedor(fornecedores.get(0));
		}
		if (!nomeUnidade.isEmpty()) {
			Unidade unidade = unidadeService.findByName(nomeUnidade);
			produto.setUnidade(unidade);
		}

		produtoService.save(produto);
		model.addAttribute("mensagem", new Mensagem("Sucesso ao cadastrar o produto", TipoMensagem.SUCESSO));
		return "redirect:/protected/produto/listar.do";
	}

	@RequestMapping(value = "/busca-nome.do", method = RequestMethod.GET)
	public String buscaPorNome(String nome, Model model) {
		List<Produto> produtos = produtoService.findByName(nome);

		model.addAttribute("produtos", produtos);
		return "produto/listaProdutos";
	}

	@RequestMapping(value = "/listar.do", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Produto> produtos = produtoService.findAll();
		model.addAttribute("produtos", produtos);
		return "produto/listaProdutos";
	}

	@RequestMapping(value = "/listarPaginados.do", method = RequestMethod.GET)
	public String list(Pageable pageable, Model model) {

		Page<Produto> produtos = produtoRepo.findAll(pageable);
		model.addAttribute("produtos", produtos.getContent());
		float nrOfPages = produtos.getTotalPages();
		model.addAttribute("maxPages", nrOfPages);
		return "produto/listaProdutosPaginados";

	}

	

	@RequestMapping(value = "/excluir.do", method = RequestMethod.GET)
	public String excluir(String idProduto, Model model) {
		produtoService.delete(idProduto);
		model.addAttribute("mensagem", new Mensagem("Sucesso ao excluir o produto", TipoMensagem.SUCESSO));
		return "forward:/protected/produto/listar.do";
	}

	@RequestMapping(value = "/preparaEdit.do", method = RequestMethod.GET)
	public String editPage(String idProduto, Model model) {
		Produto produto = produtoService.findById(idProduto);
		model.addAttribute("produto", produto);
		model.addAttribute("fornecedores", fornecedorService.findAll());
		model.addAttribute("unidades", unidadeService.findAll());
		return "produto/editaProduto";
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String editarProduto(Produto produto, String nomeFornecedor, String nomeUnidade, Model model,
			@RequestParam CommonsMultipartFile[] fileUpload) {
		
		Produto produtoBuscado = produtoService.findById(produto.getId_produto());
		if(produtoBuscado!=null){
			model.addAttribute("mensagem", new Mensagem("Código existente em outro produto, tente outro código", TipoMensagem.ERRO));
			model.addAttribute("produto",produto);
			return "forward:/protected/preparaCadastroProduto.do";
		}
		
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {
				produto.setData(aFile.getBytes());

			}
		}

		if (!nomeFornecedor.isEmpty()) {
			List<Fornecedor> fornecedores = fornecedorService.findByName(nomeFornecedor);
			produto.setFornecedor(fornecedores.get(0));
		}
		if (!nomeUnidade.isEmpty()) {
			Unidade unidade = unidadeService.findByName(nomeUnidade);
			produto.setUnidade(unidade);
		}
		produtoService.update(produto);
		model.addAttribute("mensagem", new Mensagem("Sucesso ao editar o produto", TipoMensagem.SUCESSO));
		return "redirect:/protected/produto/listar.do";
	}

	@RequestMapping(value = "/{id_produto}/getProduto.do", method = RequestMethod.GET)
	@ResponseBody
	public Produto findById(@PathVariable("id_produto") String id) {
		return produtoService.findById(id);
	}

	@RequestMapping(value = "/image.do", method = RequestMethod.GET)
	public void buscaImagem(String idProduto, HttpServletResponse response) throws IOException {
		byte[] image = produtoService.loadImage(idProduto);
		response.setContentType("image/jpeg");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(image);
		outputStream.close();
	}
}
