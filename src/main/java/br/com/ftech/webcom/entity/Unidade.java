package br.com.ftech.webcom.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Unidade implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6875806079818080987L;
	@Id
	@GeneratedValue
	private Long idUnidade;
	private String descricao;
	
	public Long getIdUnidade() {
		return idUnidade;
	}
	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
