package br.com.bdutra.ejb.facade.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import br.com.bdutra.facade.Facade;
import br.com.bdutra.model.Pessoa;

@Stateless
public class FacadePessoa {

	private static final String BRUNO = "Teste1";
	
	@EJB
	private Facade facade;
	
	@TransactionAttribute
	public Pessoa testname() {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Teste");
		
		return pessoa;
	}
}
