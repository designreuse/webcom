package br.com.ftech.webcom.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ftech.webcom.entity.VendaCabecalho;
import br.com.ftech.webcom.repository.VendaCabecalhoRepository;
import br.com.ftech.webcom.util.ConexaoMysql;
import br.com.ftech.webcom.util.Mensagem;
import br.com.ftech.webcom.util.Mensagem.TipoMensagem;

@Controller
@RequestMapping("/protected/relatorio")
public class RelatorioController {
	
	@Autowired
	private VendaCabecalhoRepository vendaCabecalhoRepository;

	@RequestMapping(value = "/relatorio.do", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
	public String relatorio() {
		return "relatorio/relatorio";
	}

	@RequestMapping(value = "/gerar-pdf.do", method = RequestMethod.POST)
	public String gerarRelatorioPDF(HttpServletRequest request, HttpServletResponse response, String dataInicio, String dataFim,Model model) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date dataInicial = null;
		Date dataFinal = null;
		try {
			String dataFormatada = dataInicio.replaceAll("/", "-");
			dataInicial = sdf.parse(dataFormatada);
			dataFormatada = dataFim.replaceAll("/", "-");
			dataFinal = sdf.parse(dataFormatada);
		} catch (ParseException e) {
		}
		
		
		if(dataInicial.equals(dataFinal)){
			LocalDate df =  new LocalDate(dataFinal);
			dataFinal = df.plusDays(1).toDate();
		}
		
		List<VendaCabecalho>vendas = vendaCabecalhoRepository.buscaVendaPorPeriodo(dataInicial, dataFinal);
		if(vendas.isEmpty()){
			model.addAttribute("mensagem", new Mensagem("Período sem registro de vendas", TipoMensagem.AVISO));
			return  "forward:/protected/relatorio/relatorio.do";

		}
		
		
		
		Connection con = new ConexaoMysql().getConnection();
		HashMap parametros = new HashMap();
		parametros.put("dataInicio", dataInicial);
		parametros.put("dataFim", dataFinal);

		InputStream is = this.getClass().getClassLoader().getResourceAsStream("relatorios/vendas-produto.jasper");

		try {
			JasperPrint jp = JasperFillManager.fillReport(is, parametros, con);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos.write(JasperExportManager.exportReportToPdf(jp));
			baos.close();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=relatorio-vendas.pdf");
			// response.setHeader("Content-disposition",
			// "attachment; filename=relatorio-vendas.pdf");
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			ServletOutputStream sos;
			sos = response.getOutputStream();
			baos.writeTo(sos);
			sos.flush();
			sos.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return  null;

	}
	
	@RequestMapping(value = "/relatorio-produto-codigo.do", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
	public String geraRelatorioProdutosCodigo(HttpServletRequest request, HttpServletResponse response) {
		
		Connection con = new ConexaoMysql().getConnection();
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("relatorios/produto-codigo.jasper");
		
		try {
			JasperPrint jp = JasperFillManager.fillReport(is, null, con);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos.write(JasperExportManager.exportReportToPdf(jp));
			baos.close();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=relatorio-produtos-codigos.pdf");
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			ServletOutputStream sos;
			sos = response.getOutputStream();
			baos.writeTo(sos);
			sos.flush();
			sos.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
